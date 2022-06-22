package jp.co.biglobe.isp.kintai.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record AttendanceEndTime(LocalTime value) {
    private static final DateTimeFormatter FORMAT_ATTENDANCE_TIME = DateTimeFormatter.ofPattern("kkss");

    public static AttendanceEndTime of(String time) {
        return new AttendanceEndTime(LocalTime.parse(time, FORMAT_ATTENDANCE_TIME));
    }
}