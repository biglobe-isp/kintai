package com.naosim.dddwork.domain.date;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Day {
    @Getter
    private final int day;

    public Day(int day) {
        if (!isCorrectDayValue(day)) {
            throw new RuntimeException("Incorrect Day Value!");
        }
        this.day = day;
    }

    private boolean isCorrectDayValue(int day) {
        return day >= 1 && day <= 31;
    }

    //public int getDay()
    //{
    //    return day;
    //}
}
