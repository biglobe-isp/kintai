package com.naosim.dddwork.kintai.datasource.timerecord.factory;

import com.naosim.dddwork.kintai.datasource.timerecord.csv.WorkedTimeCsvDataSource;
import com.naosim.dddwork.kintai.datasource.timerecord.protocol.DataSourceFactory;
import com.naosim.dddwork.kintai.domain.repository.protocol.WorkedTimeRepository;


/**
 * CSVデータソースファクトリ
 */
public class CsvDataSourceFactory implements DataSourceFactory {

    @Override
    public WorkedTimeRepository workedTimeRepository() {
        return new WorkedTimeCsvDataSource();
    }
}
