package com.naosim.dddwork.domain;

import lombok.Value;

@Value
public class TimeUnit {
    int hour;
    int minutes;

    private TimeUnit(final int hour, final int minutes) {
        this.hour = hour + (minutes / 60);
        this.minutes = (minutes % 60);
    }

    public static TimeUnit of(final int hour, final int minutes) {
        return new TimeUnit(hour, minutes);
    }

    public static TimeUnit of(final int minutes) {
        return new TimeUnit(0, minutes);
    }

    public int getTotalMinutes() {
        return (this.hour * 60) + this.minutes;
    }

    public String getMonthlyTotalString() {
        return this.getHour() + "時間" + this.getMinutes() + "分";
    }
}
