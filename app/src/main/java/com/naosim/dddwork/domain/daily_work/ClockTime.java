package com.naosim.dddwork.domain.daily_work;

import java.time.DateTimeException;
import java.time.LocalTime;

/**
 * 時刻
 */
public class ClockTime {
    LocalTime moment;

    public ClockTime(int hour, int minute){
        try {
            moment = LocalTime.of(hour, minute);
        } catch (DateTimeException e) {
            throw new DateTimeException("時または分が不正値です。");
        }
    }

    ClockTime(ClockTime timeMoment){
        this.moment = timeMoment.moment;
    }
}