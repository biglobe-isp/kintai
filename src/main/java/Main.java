
import kintai.api.AttendanceApi;
import kintai.datasource.AttendanceRepositoryCsv;
import kintai.domain.AttendanceRepository;
import kintai.domain.EmployeeRule;
import kintai.service.AttendanceService;

import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // DI, 設定ファイル相当のコードはそれほど量がないため main に記載
        AttendanceRepository repository = new AttendanceRepositoryCsv(Paths.get("data.csv"));
        AttendanceService service = new AttendanceService(repository);
        EmployeeRule employeeRule = EmployeeRule.defaultEmployeeRule();

        AttendanceApi api = new AttendanceApi(service, employeeRule);

        // CLI ライブラリ相当のコードは main に残す
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }
                api.input(args[1], args[2], args[3], LocalDateTime.now());
            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                api.total(args[1], System.out);
            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}