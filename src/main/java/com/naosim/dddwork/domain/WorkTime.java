package com.naosim.dddwork.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/***
 * 勤務の時刻を表す値オブジェクト
 */
public class WorkTime {
    private LocalTime lt;

    public WorkTime(String time) {
        int hour = Integer.valueOf(time.substring(0, 2));
        int minute = Integer.valueOf(time.substring(2, 4));
        this.lt = LocalTime.of(hour, minute);
    }

    public int getHour() {
        return this.lt.getHour();
    }

    public int getMinute() {
        return this.lt.getMinute();
    }

    public LocalTime getLocalTIme() {
        return this.lt;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return this.lt.format(formatter);
    }
}
