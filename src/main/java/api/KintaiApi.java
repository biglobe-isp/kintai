package api;

import datasource.InputCsvRepositoryImpl;
import datasource.TotalCsvRepositoryImpl;
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
                InputCsvRepositoryImpl inputCsvRepositoryImpl = new InputCsvRepositoryImpl();
                new InputService(inputData, inputCsvRepositoryImpl);
            } else if ("total".equals(methodType)) {
                TotalCsvRepositoryImpl totalCsvRepository = new TotalCsvRepositoryImpl();
                new TotalService(inputData, totalCsvRepository);
            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
