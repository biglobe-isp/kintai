package com.naosim.dddwork.domain;

import lombok.Value;

import java.time.LocalTime;

@Value
public class TimePoint {
    int hour;
    int minutes;

    public static TimePoint of(LocalTime localTime) {
        return TimePoint.of(localTime.getHour(), localTime.getMinute());
    }

    public static TimePoint of(int hour, int minutes) {
        return new TimePoint(hour, minutes);
    }

    public LocalTime getLocalTime() {
        return LocalTime.of(hour, minutes);
    }
}
