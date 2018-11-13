package api;

import datasource.InputCsvRepositoryImpl;
import datasource.TotalCsvRepositoryImpl;
import form.InputDataForm;
import service.InputService;
import service.TotalService;

public class KintaiApi {

    public static void main(String[] inputData) {
        // APIからドメインを呼ぶのはOK
        // サービス層を飛ばして呼ぶのもOK
        try {
            //入力チェック
            new InputDataForm(inputData);

            String methodType = inputData[0];
            if ("input".equals(methodType)) {
                InputCsvRepositoryImpl inputCsvRepository = new InputCsvRepositoryImpl();
                new InputService(inputData, inputCsvRepository);
            } else if ("total".equals(methodType)) {
                TotalCsvRepositoryImpl totalCsvRepository = new TotalCsvRepositoryImpl();
                new TotalService(inputData, totalCsvRepository);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
