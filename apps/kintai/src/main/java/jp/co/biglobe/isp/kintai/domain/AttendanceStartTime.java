package jp.co.biglobe.isp.kintai.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record AttendanceStartTime(LocalTime value) {
    private static final DateTimeFormatter FORMAT_ATTENDANCE_TIME = DateTimeFormatter.ofPattern("kkss");

    public static AttendanceStartTime of(String time) {
        return new AttendanceStartTime(LocalTime.parse(time, FORMAT_ATTENDANCE_TIME));
    }
}
