package jp.co.biglobe.isp.kintai.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record AttendanceStartTime(LocalTime value) {
    private static final DateTimeFormatter FORMAT_ATTENDANCE_TIME = DateTimeFormatter.ofPattern("HHmm");
    private static final LocalTime LATEST_ATTENDANCE_TIME = LocalTime.parse("0900", FORMAT_ATTENDANCE_TIME);

    public AttendanceStartTime(LocalTime value) {
        System.out.println(value);
        if (value.isAfter(LATEST_ATTENDANCE_TIME)) {
            throw new RuntimeException("遅刻は認められていません");
        }
        this.value = value;
    }

    public static AttendanceStartTime of(String time) {
        return new AttendanceStartTime(LocalTime.parse(time, FORMAT_ATTENDANCE_TIME));
    }
}
