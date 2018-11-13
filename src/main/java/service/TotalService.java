package service;

import datasource.TotalCsvRepositoryImpl;
import domain.YearMonthDomain;

public class TotalService {
    public TotalService(String[] inputData, TotalCsvRepositoryImpl totalCsvRepository) {
        YearMonthDomain yearMonthDomain = new YearMonthDomain(inputData[1]);

        // CSV出力
        totalCsvRepository.TotalCsvAdd(yearMonthDomain);
    }
}
