package com.naosim.dddwork.kintai.api.settings;

import com.naosim.dddwork.kintai.datasource.workedtime.factory.CsvDataSourceFactory;
import com.naosim.dddwork.kintai.datasource.workedtime.protocol.DataSourceFactory;


/**
 * データストア・ポリシー
 */
public enum DataStorePolicy {

    CSV(new CsvDataSourceFactory()),
    ;

    private final DataSourceFactory _dataSourceFactory;

    DataStorePolicy(DataSourceFactory dataSourceFactory) {
        _dataSourceFactory = dataSourceFactory;
    }


    public DataSourceFactory dataSourceFactory() {
        return _dataSourceFactory;
    }
}
