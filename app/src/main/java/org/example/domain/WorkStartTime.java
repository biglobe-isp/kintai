package org.example.domain;

import lombok.Value;

import java.time.LocalTime;

import static org.example.utils.DateTimeFormatters.HOUR_HYPHEN_MINUTE;
import static org.example.utils.DateTimeFormatters.HOUR_MINUTE;

@Value
public class WorkStartTime {
    LocalTime value;

    public static WorkStartTime of(String value) {
        try {
            LocalTime localTime = LocalTime.parse(value, HOUR_HYPHEN_MINUTE);

            if (localTime.getHour() == 12 || localTime.getHour() == 18 || localTime.getHour() == 21) {
                return new WorkStartTime(LocalTime.of(localTime.getHour() + 1, 0));
            } else {
                return new WorkStartTime(localTime);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid time format: " + value, e);
        }
    }

    public int convertToMinutes() {
        return value.getHour() * 60 + value.getMinute();
    }
}
