package service;

import datasource.InputCsvRepositoryImpl;
import domain.OverWorkMinutesDomain;
import domain.WorkMinutesDomain;

public class InputService {
    public InputService(String[] inputData, InputCsvRepositoryImpl inputCsvRepositoryImpl) {
        if (inputData.length < 4) {
            throw new RuntimeException("引数が足りません");
        }

        WorkMinutesDomain workMinutesDomain = new WorkMinutesDomain(inputData[2], inputData[3]);
        OverWorkMinutesDomain overWorkMinutesDomain = new OverWorkMinutesDomain(workMinutesDomain);

        // CSV出力
        inputCsvRepositoryImpl.InputCsvAdd(inputData, workMinutesDomain.workMinutes, overWorkMinutesDomain.overWorkMinutes);
    }
}
