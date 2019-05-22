package com.naosim.dddwork.kintai.datasource.settings;

import com.naosim.dddwork.kintai.datasource.settings.option.DataStorePolicy;


//TODO: フィールドが public なのに setupでがイマイチ...
//      setup の引数もイマイチ。RDBとかになったら csvFileNameは要らない...
/**
 * データソース設定
 */
public final class DataSourceConfiguration {

    public static DataStorePolicy DATA_STORE_POLICY = DataStorePolicy.CSV;
    public static String CSV_FILE_NAME = "data.csv";

    public static void setup(DataStorePolicy dataStorePolicy, String csvFileName) {

        DATA_STORE_POLICY = dataStorePolicy;
        CSV_FILE_NAME = csvFileName;
    }
}
