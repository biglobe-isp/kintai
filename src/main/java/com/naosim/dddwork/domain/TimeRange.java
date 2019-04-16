package com.naosim.dddwork.domain;

import lombok.Value;

@Value
public class TimeRange {

    private final TimePoint startTime;
    private final TimePoint endTime;

    public static TimeRange of(int startHour, int startMinute, int endHour, int endMinute) {
        return new TimeRange(
                TimePoint.of(startHour, startMinute),
                TimePoint.of(endHour, endMinute)
        );
    }
}
