package com.naosim.dddwork.kintai.domain.core.type.time.component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * 時刻
 */
@EqualsAndHashCode
@ToString
@Getter
//TODO: Getterは暫定的に使用中
public class TimeOfDay {

    final HourOfDay hour;
    final MinuteOfHour minute;


    public static TimeOfDay of(int hour, int minute) {
        return new TimeOfDay(HourOfDay.of(hour), MinuteOfHour.of(minute));
    }

    public static TimeOfDay of(HourOfDay hour, MinuteOfHour minute) {
        return new TimeOfDay(hour, minute);
    }

    public TimeOfDay(HourOfDay hour, MinuteOfHour minute) {

        this.hour = hour;
        this.minute = minute;
    }

}
