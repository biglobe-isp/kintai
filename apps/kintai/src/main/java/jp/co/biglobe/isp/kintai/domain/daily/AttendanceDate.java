package jp.co.biglobe.isp.kintai.domain.daily;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record AttendanceDate(LocalDate value) {
    public static AttendanceDate of(String date) {
        return new AttendanceDate(LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE));
    }
}
