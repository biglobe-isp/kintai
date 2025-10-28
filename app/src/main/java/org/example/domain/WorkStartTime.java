package org.example.domain;

import lombok.Value;

import java.time.LocalTime;

import static org.example.utils.DateTimeFormatters.HOUR_HYPHEN_MINUTE;

@Value
public class WorkStartTime {
    LocalTime value;

    public static WorkStartTime of(String value) {
        try {
            LocalTime localTime = LocalTime.parse(value, HOUR_HYPHEN_MINUTE);
            return new WorkStartTime(localTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid time format: " + value, e);
        }
    }
}
