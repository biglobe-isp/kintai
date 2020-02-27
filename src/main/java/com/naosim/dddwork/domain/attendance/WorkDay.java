package com.naosim.dddwork.domain.attendance;

import lombok.Value;

import java.time.LocalDate;

@Value
public class WorkDay {
    LocalDate date;

    public static WorkDay of(String workDay) {
        return new WorkDay(workDay);
    }

    public WorkDay(String workDay) {
        int year = Integer.parseInt(workDay.substring(0, 4));
        int month = Integer.parseInt(workDay.substring(4, 6));
        int dayOfMonth = Integer.parseInt(workDay.substring(6));
        date = LocalDate.of(year, month, dayOfMonth);
    }
}
