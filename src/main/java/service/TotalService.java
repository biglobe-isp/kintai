package service;

import datasource.TotalCsvDatasouce;
import domain.YearMonthDomain;

public class TotalService {
    public TotalService(String[] inputData) {
        if (inputData.length < 2) {
            throw new RuntimeException("引数が足りません");
        }
        YearMonthDomain yearMonthDomain = new YearMonthDomain(inputData[1]);

        // CSV出力
        new TotalCsvDatasouce(yearMonthDomain);
    }
}
