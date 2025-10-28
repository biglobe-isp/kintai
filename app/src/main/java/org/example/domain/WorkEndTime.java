package org.example.domain;

import lombok.Value;

import java.time.LocalTime;

import static org.example.utils.DateTimeFormatters.HOUR_HYPHEN_MINUTE;
import static org.example.utils.DateTimeFormatters.HOUR_MINUTE;

@Value
public class WorkEndTime {
    LocalTime value;

    public static WorkEndTime of(String value) {
        try {
            LocalTime localTime = LocalTime.parse(value, HOUR_HYPHEN_MINUTE);
            return new WorkEndTime(localTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid time format: " + value, e);
        }
    }
}
