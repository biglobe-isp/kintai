package jp.co.biglobe.isp.kintai.api;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator {
    public static void isValid(String yearMonthStr) {
        try {
            YearMonth.parse(yearMonthStr, DateTimeFormatter.ofPattern("yyyyMM"));
        } catch (RuntimeException e){
            throw new RuntimeException("年月が不正です.");
        }
    }
}
