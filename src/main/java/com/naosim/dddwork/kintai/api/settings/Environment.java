package com.naosim.dddwork.kintai.api.settings;


public class Environment {


    public static String DATA_STORE_CSV_FILE_NAME = "data.csv";

    /**
     * テストで System.out をスパイしてアサーションしているため、
     * デバッグプリントするとアサーションは失敗するのだ！
     */
    public static boolean DEBUG = false;
}
