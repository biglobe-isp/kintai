import api.KintaiCountUpApi;
import api.KintaiRegisterRequest;
import api.KintaiRegitsterApi;
import domain.TotallyMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
    @Autowired
    KintaiRegisterRequest kintaiRegisterRequest;

    public static void main(String[] args) {

        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }
                KintaiRegisterRequest kintaiRegisterRequest = new KintaiRegisterRequest(args);
                KintaiRegitsterApi.kintaRegisterApi(kintaiRegisterRequest);

            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                String yearMonth = args[1];
                //依存が逆転しているような
                KintaiCountUpApi.coutupApi(new TotallyMonth(yearMonth));

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
