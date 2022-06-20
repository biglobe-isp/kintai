package apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record AttendanceTime(LocalTime value) {
    private static final DateTimeFormatter FORMAT_ATTENDANCE_TIME = DateTimeFormatter.ofPattern("kkss");

    public static AttendanceTime of(String time) {
        return new AttendanceTime(LocalTime.parse(time, FORMAT_ATTENDANCE_TIME));
    }
}
