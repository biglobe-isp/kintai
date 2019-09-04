package com.naosim.dddwork.domain.time;

import lombok.Getter;

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
        return minute >= 0 && minute <= 59;
    }

}
