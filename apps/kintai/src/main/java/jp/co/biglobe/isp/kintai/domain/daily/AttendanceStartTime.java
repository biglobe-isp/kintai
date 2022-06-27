package jp.co.biglobe.isp.kintai.domain.daily;

import java.time.LocalTime;

public record AttendanceStartTime(LocalTime value) {
    /**
     * 勤務時間開始時刻
     */
    private static final LocalTime LATEST_ATTENDANCE_TIME = LocalTime.of(9, 00);

    public AttendanceStartTime(LocalTime value) {
        System.out.println(value);
        if (value.isAfter(LATEST_ATTENDANCE_TIME)) {
            throw new RuntimeException("遅刻は認められていません");
        }
        this.value = value;
    }
}
