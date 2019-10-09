package com.naosim.dddwork.domain;

import java.time.LocalTime;

/**
 * 休憩時間
 */
public class Rest {
    private LocalTime start;
    private LocalTime end;

    public Rest(int startHour, int startMinute, int endHour, int endMinuite) {
        this.start = LocalTime.of(startHour, startMinute);
        this.end = LocalTime.of(endHour, endMinuite);
    }

    public LocalTime getStartLocalTime() {
        return this.start;
    }

    public LocalTime getEndLocalTime() {
        return this.end;
    }
}
