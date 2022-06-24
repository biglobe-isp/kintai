package jp.co.biglobe.isp.kintai.api;

import jp.co.biglobe.isp.kintai.datasource.AttendanceRepositoryCsv;
import jp.co.biglobe.isp.kintai.domain.DailyAttendanceFactory;
import jp.co.biglobe.isp.kintai.service.AttendanceManagementService;
import jp.co.biglobe.isp.kintai.service.AttendanceRepository;

import java.nio.file.Path;
import java.time.Clock;

public class Main {
    private static final  Clock clock = Clock.systemDefaultZone();
    private static final Path csvFilePath = Path.of("./output/data.csv");
    private static final AttendanceRepository repository = new AttendanceRepositoryCsv(csvFilePath, clock);
    private static final AttendanceManagementService service = new AttendanceManagementService(repository);
    private static final DailyAttendanceFactory dailyAttendanceFactory = new DailyAttendanceFactory();

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            switch (MethodType.of(args[0])) {
                case INPUT -> input(args);
                case TOTAL -> System.out.println("集計機能は未実装です。");
                default -> throw new RuntimeException("引数のメソッドタイプは定義されていません。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void input(String[] args) {
        if (args.length < 4) {
            throw new RuntimeException("引数が不足しています。");
        }
        final InputArgs inputArgs = new InputArgs(args[1], args[2], args[3]);
        service.inputAttendance(dailyAttendanceFactory.create(inputArgs));
    }
}
