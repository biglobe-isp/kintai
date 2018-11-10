package service;

import datasource.InputCsvDatasource;
import domain.OverWorkMinutesDomain;
import domain.WorkMinutesDomain;

public class InputService {
    public InputService(String[] inputData) {
        if (inputData.length < 4) {
            throw new RuntimeException("引数が足りません");
        }

        WorkMinutesDomain workMinutesDomain = new WorkMinutesDomain(inputData[2], inputData[3]);
        OverWorkMinutesDomain overWorkMinutesDomain = new OverWorkMinutesDomain(workMinutesDomain);

        // CSV出力
        new InputCsvDatasource(inputData, workMinutesDomain.workMinutes, overWorkMinutesDomain.overWorkMinutes);
    }
}
