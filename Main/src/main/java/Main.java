import api.KintaiCountUpApi;
import api.KintaiCountUpRequest;
import api.KintaiRegisterRequest;
import api.KintaiRegitsterApi;

public class Main {

    public static void main(String[] args) {
        //メソッドタイプだけのif文にしたい

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
                KintaiRegitsterApi kintaiRegitsterApi = new KintaiRegitsterApi();
                kintaiRegitsterApi.kintaRegisterApi(kintaiRegisterRequest);

            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                KintaiCountUpRequest kintaiCountUpRequest = new KintaiCountUpRequest(args);
                KintaiCountUpApi kintaiCountUpApi = new KintaiCountUpApi();
                kintaiCountUpApi.coutupApi(kintaiCountUpRequest);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
