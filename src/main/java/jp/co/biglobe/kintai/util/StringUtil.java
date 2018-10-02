package jp.co.biglobe.kintai.util;

public class StringUtil {

    public static boolean isNullorEmpty(final String value){
        return value == null ? true : value.length() <= 0;
    }
}
