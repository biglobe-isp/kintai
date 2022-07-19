package jp.co.biglobe.isp.kintai.domain.daily;

import com.google.common.collect.Comparators;

import java.time.LocalTime;
import java.time.temporal.Temporal;

public record AttendanceEndTime(LocalTime value) {
    public boolean isBefore(LocalTime other) {
        return value.isBefore(other);
    }

    public Temporal min(LocalTime other) {
        return Comparators.min(value, other);
    }
}
