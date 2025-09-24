package com.naosim.dddwork.domain;

public final class Minute {
    private final int minute;

    public Minute(int minute) {
        if (minute > 60 || minute < 0) {
            throw new IllegalArgumentException("0~60までの数字でなければなりません。");
        }
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }
}
