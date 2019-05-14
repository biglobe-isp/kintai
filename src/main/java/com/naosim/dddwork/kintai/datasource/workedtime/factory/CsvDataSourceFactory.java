package com.naosim.dddwork.kintai.datasource.workedtime.factory;

import com.naosim.dddwork.kintai.datasource.workedtime.csv.WorkedTimeCsvDataSource;
import com.naosim.dddwork.kintai.datasource.workedtime.protocol.DataSourceFactory;
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
