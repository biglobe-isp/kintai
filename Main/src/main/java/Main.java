import api.KintaiCountUpApi;
import api.KintaiRegitsterApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
//java Main input 20170101 0900 1800
public class Main {
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
                KintaiRegitsterApi.kintaRegisterApi(args);

            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                KintaiCountUpApi.coutupApi(args);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
