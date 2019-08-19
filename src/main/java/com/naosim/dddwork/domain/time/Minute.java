package com.naosim.dddwork.domain.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Minute {
    @Getter
    private final int minute;

    public Minute(int minute)
    {
        if(!isCorrectMinuteValue(minute))
        {
            throw new RuntimeException("Incorrect minute value.");
        }
        this.minute = minute;
    }

    private boolean isCorrectMinuteValue(int minute)
    {
        if(minute < 0 || minute > 59)
        {
            return false;
        }
        return true;
    }

    public int getMinute()
    {
        return minute;
    }
}
