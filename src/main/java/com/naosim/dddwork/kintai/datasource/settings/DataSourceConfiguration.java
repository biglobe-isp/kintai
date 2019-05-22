package com.naosim.dddwork.kintai.datasource.settings;

import com.naosim.dddwork.kintai.datasource.settings.option.DataStorePolicy;
import com.naosim.dddwork.kintai.datasource.timerecord.protocol.DataSourceFactory;


/**
 * データソース設定
 */
public final class DataSourceConfiguration {

    private static DataSourceConfiguration _singleton;

    public static void load(DataSourceConfiguration dataSourceConfiguration) {
        _singleton = dataSourceConfiguration;
    }


    public static DataStorePolicy dataStorePolicy() {
        return _singleton.dataStorePolicy;
    }

    public static String csvFileName() {
        return _singleton.csvFileName;
    }

    public static DataSourceFactory dataSourceFactory() {
        return dataStorePolicy().dataSourceFactory();
    }


    /* インスタンス */

    public final DataStorePolicy dataStorePolicy;
    public final String csvFileName;

    public DataSourceConfiguration(DataStorePolicy dataStorePolicy, String csvFileName) {
        this.dataStorePolicy = dataStorePolicy;
        this.csvFileName = csvFileName;
    }


    /* ビルダー */

    public static DataStorePolicySpec builder() {
        return new Builder();
    }


    public interface DataStorePolicySpec {
        Builder dataStorePolicy(DataStorePolicy policy);
    }

    public interface CsvFileNameSpec {
        Builder csvFileName(String csvFileName);
    }


    public static class Builder implements DataStorePolicySpec,
                                           CsvFileNameSpec {

        private DataStorePolicy _dataStorePolicy;
        private String _csvFileName;

        private Builder() {
        }

        @Override
        public Builder dataStorePolicy(DataStorePolicy policy) {
            _dataStorePolicy = policy;
            return this;
        }

        @Override
        public Builder csvFileName(String csvFileName) {
            _csvFileName = csvFileName;
            return this;
        }

        public void build() {
            DataSourceConfiguration.load(new DataSourceConfiguration(_dataStorePolicy, _csvFileName));
        }
    }

}
