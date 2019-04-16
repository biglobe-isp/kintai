package com.naosim.dddwork.domain;

import lombok.Value;

@Value
public class TimePoint {

    private final int hour;
    private final int minute;

    public int toMinuteValue() {
        return hour * 60 + minute;
    }

    public int differenceMinuteValue(TimePoint timePoint) {
        return this.toMinuteValue() - timePoint.toMinuteValue();
    }

    public boolean isBefore(TimePoint timePoint) {
        return differenceMinuteValue(timePoint) < 0;
    }

    public boolean isAfter(TimePoint timePoint) {
        return differenceMinuteValue(timePoint) > 0;
    }

    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }

    public static TimePoint of(int hour, int minute) {
        return new TimePoint(hour, minute);
    }

    public static TimePoint parse(String value) {
        if (value == null) throw new IllegalArgumentException();
        String[] items = value.split(":");
        return TimePoint.of(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
    }
}
