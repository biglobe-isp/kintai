package service;

import datasource.TotalCsvRepositoryImpl;
import domain.YearMonthDomain;

public class TotalService {
    public TotalService(String[] inputData, TotalCsvRepositoryImpl totalCsvRepository) {
        if (inputData.length < 2) {
            throw new RuntimeException("引数が足りません");
        }
        YearMonthDomain yearMonthDomain = new YearMonthDomain(inputData[1]);

        // CSV出力
        totalCsvRepository.TotalCsvAdd(yearMonthDomain);
    }
}
