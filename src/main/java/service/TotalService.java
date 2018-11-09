package service;

import datasource.TotalCsvDatasouce;
import domain.YearMonthDomain;

public class TotalService {
    public TotalService(String[] inputData) {
        YearMonthDomain yearMonthDomain = new YearMonthDomain(inputData[1]);
        if (inputData.length < 2) {
            throw new RuntimeException("引数が足りません");
        }

        // CSV出力
        new TotalCsvDatasouce(yearMonthDomain);
    }
}
