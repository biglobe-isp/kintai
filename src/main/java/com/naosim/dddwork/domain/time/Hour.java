package com.naosim.dddwork.domain.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Hour {
    @Getter
    private final int hour;

    public Hour(int hour) {
        // check value is correct
        if (!isCorrectHourValue(hour)) {
            throw new RuntimeException("Illegal hour value");
        }
        this.hour = hour;
    }

    private boolean isCorrectHourValue(int hour) {
        // in case of overtime work after midnight AM00:00
        return hour >= 0 && hour <= 47;
    }
}
