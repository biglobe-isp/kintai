package com.naosim.dddwork.kintai.domain.model.foundation.time;


import com.naosim.dddwork.kintai.domain.core.type.time.component.HourOfDay;
import com.naosim.dddwork.kintai.domain.core.type.time.component.MinuteOfHour;
import com.naosim.dddwork.kintai.domain.core.type.time.component.TimeOfDay;


/**
 * 始業時刻
 */
public class BeginTime {

    final TimeOfDay timeOfDay;


    public static BeginTime of(int hour, int minute) {
        return new BeginTime(HourOfDay.of(hour), MinuteOfHour.of(minute));
    }

    public static BeginTime of(HourOfDay hour, MinuteOfHour minute) {
        return new BeginTime(hour, minute);
    }

    public BeginTime(int hour, int minute) {
        timeOfDay = TimeOfDay.of(hour, minute);
    }

    public BeginTime(HourOfDay hour, MinuteOfHour minute) {
        timeOfDay = TimeOfDay.of(hour, minute);
    }


    public int hourRawValue() {
        return timeOfDay.hourRawValue();
    }

    public int minuteRawValue() {
        return timeOfDay.minuteRawValue();
    }

    public String storedValue() {
        return timeOfDay.defaultStoredValue();
    }
}
