package com.naosim.dddwork.domain.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class RecordedTime implements Comparable<RecordedTime>{

    @Getter
    Hour hour;
    @Getter
    Minute minute;

    public RecordedTime(Hour hour, Minute minute)
    {
        this.hour = hour;
        this.minute = minute;
    }

    public int getValue()
    {
        int iMinutes = minute.getMinute();
        int iHour = hour.getHour();

        return iHour * 100 +  iMinutes;
    }

    public Hour getHour() { return hour; }
    public Minute getMinute() { return minute; }

    @Override
    public int compareTo(RecordedTime o) {
        return this.getValue() - o.getValue();
    }
    public String toString()
    {
        return String.format("%04d",this.getValue());
    }
}
