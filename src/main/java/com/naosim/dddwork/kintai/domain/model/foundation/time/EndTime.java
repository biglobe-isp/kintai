package com.naosim.dddwork.kintai.domain.model.foundation.time;


import com.naosim.dddwork.kintai.domain.core.type.time.component.HourOfDay;
import com.naosim.dddwork.kintai.domain.core.type.time.component.MinuteOfHour;
import com.naosim.dddwork.kintai.domain.core.type.time.component.TimeOfDay;


/**
 * 終業時刻
 */
public class EndTime {

    final TimeOfDay timeOfDay;


    public static EndTime of(int hour, int minute) {
        return new EndTime(hour, minute);
    }

    public static EndTime of(HourOfDay hour, MinuteOfHour minute) {
        return new EndTime(hour, minute);
    }

    public EndTime(int hour, int minute) {
        timeOfDay = TimeOfDay.of(hour, minute);
    }

    public EndTime(HourOfDay hour, MinuteOfHour minute) {
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
