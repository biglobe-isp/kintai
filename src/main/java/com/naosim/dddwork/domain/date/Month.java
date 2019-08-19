package com.naosim.dddwork.domain.date;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Month {
    @Getter
    private final int month;

    public Month(int month)
    {
        if(!isCorrectMonthValue(month))
        {
            throw new RuntimeException("Incorrect month value.");
        }
        this.month = month;
    }

    private boolean isCorrectMonthValue(int month)
    {
        if(month < 1 || month > 12)
        {
            return false;
        }
        return true;
    }

    public int getMonth()
    {
        return month;
    }
}
