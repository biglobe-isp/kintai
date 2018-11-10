package api;

import service.InputService;
import service.TotalService;

public class KintaiApi {

    public static void main(String[] inputData) {
        new KintaiApi(inputData);
    }

    KintaiApi(String[] inputData) {
        try {
            if (inputData.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = inputData[0];

            if ("input".equals(methodType)) {
                new InputService(inputData);
            } else if ("total".equals(methodType)) {
                new TotalService(inputData);
            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
