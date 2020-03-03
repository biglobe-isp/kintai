package com.naosim.dddwork.domain;

import com.google.common.base.Strings;
import lombok.Value;

@Value
public class TimePoint {
    int hour;
    int minutes;

    public static TimePoint of(int hour, int minutes) {
        return new TimePoint(hour, minutes);
    }

    public static TimePoint of(String hourMinutes) {
        String value = Strings.padStart(hourMinutes, 4, '0');
        int hour = Integer.parseInt(value.substring(0, 1));
        int minutes = Integer.parseInt(value.substring(2));
        return new TimePoint(hour, minutes);
    }

    public int getIntValue() {
        return hour * 60 + minutes;
    }

    public String toString() {
        return Strings.padStart(String.valueOf(hour), 2, '0')
                + Strings.padStart(String.valueOf(minutes), 2, '0');
    }
}
