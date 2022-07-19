package jp.co.biglobe.isp.kintai.domain.daily;

import com.google.common.collect.Comparators;

import java.time.LocalTime;

public record AttendanceStartTime(LocalTime value) {
    /**
     * 勤務時間開始時刻
     */
    private static final LocalTime LATEST_ATTENDANCE_TIME = LocalTime.of(9, 0);

    public AttendanceStartTime {
        if (value.isAfter(LATEST_ATTENDANCE_TIME)) {
            throw new RuntimeException("遅刻は認められていません");
        }
    }

    public boolean isAfter(LocalTime other) {
        return value.isAfter(other);
    }

    public LocalTime max(LocalTime other) {
        return Comparators.max(value, other);
    }
}
