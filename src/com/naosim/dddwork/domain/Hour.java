package com.naosim.dddwork.domain;

public final class Hour {
    private final int hour;

    public Hour(int hour) {
        if (hour > 24 || hour < 0) {
            throw new IllegalArgumentException("時は0~24までです");
        }
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }
}
