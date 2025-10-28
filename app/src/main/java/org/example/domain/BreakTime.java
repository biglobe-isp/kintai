package org.example.domain;

import lombok.Value;

import java.time.LocalTime;

@Value
public class BreakTime {
    LocalTime breakStartTime;
    LocalTime breakEndTime;

    public int getBreakMinute(LocalTime workStartTime, LocalTime workEndTime) {
        if (breakStartTime.isAfter(workStartTime) &&
                breakEndTime.isBefore(workEndTime)) {
            return (breakEndTime.getHour() - breakStartTime.getHour()) * 60
                    + (breakEndTime.getMinute() - breakStartTime.getMinute());
        }

        if (workEndTime.isAfter(breakStartTime) &&
                workEndTime.isBefore(breakEndTime)) {
            return (workEndTime.getHour() - breakStartTime.getHour()) * 60
                    + (workEndTime.getMinute() - breakStartTime.getMinute());
        }

        if (workStartTime.isAfter(breakStartTime) &&
                workStartTime.isBefore(breakEndTime)) {
            return (breakEndTime.getHour() - workStartTime.getHour()) * 60
                    + (breakEndTime.getMinute() - workStartTime.getMinute());
        }

        return 0;
    }
}