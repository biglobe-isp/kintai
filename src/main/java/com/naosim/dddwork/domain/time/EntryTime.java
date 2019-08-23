package com.naosim.dddwork.domain.time;


import java.io.Serializable;

public class EntryTime {

    Hour hour;
    Minute minute;

    public EntryTime(Hour hour, Minute minute)
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

    public String toString()
    {
        return String.format("%04d",this.getValue());
    }
}
