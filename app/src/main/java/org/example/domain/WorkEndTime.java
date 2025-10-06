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

            if (localTime.getHour() == 12 || localTime.getHour() == 18 || localTime.getHour() == 21) {
                return new WorkEndTime(LocalTime.of(localTime.getHour(), 0));
            } else {
                return new WorkEndTime(localTime);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid time format: " + value, e);
        }
    }

    public int convertToMinutes() {
        return value.getHour() * 60 + value.getMinute();
    }
}
