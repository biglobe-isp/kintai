package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.time.EntryTime;
import lombok.Getter;

import java.time.LocalDateTime;

// TODO
// - change working duration to start date & end date
// - create new classes in domain total working hours , working hours without break,
// and regular working hours and over time working hours.

public class AttendanceRecord {
    @Getter
    private final WorkingDate workingDate;
    @Getter
    private final EntryTime startTime;
    @Getter
    private final EntryTime endTime;

    public AttendanceRecord(WorkingDate workingDate, EntryTime startTime, EntryTime endTime) {
        this.workingDate = workingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartLocalDateTime() {
        return LocalDateTime.of(
                workingDate.getYear().getYear(),
                workingDate.getMonth().getMonth(),
                workingDate.getDay().getDay(),
                startTime.getHour().getHour(),
                startTime.getMinute().getMinute()
        );
    }

    public LocalDateTime getEndLocalDateTime() {
        // if hour > 24 , adjust date to the next day because LocalDateTime hour allows from 0 to 23
        final int MAXIMUM_HOUR_VALUE = 24;
        int hour = endTime.getHour().getHour();
        int adjustedHour = hour >= MAXIMUM_HOUR_VALUE ? hour - MAXIMUM_HOUR_VALUE : hour;
        LocalDateTime localDateTime = LocalDateTime.of(
                workingDate.getYear().getYear(),
                workingDate.getMonth().getMonth(),
                workingDate.getDay().getDay(),
                adjustedHour,
                endTime.getMinute().getMinute()
        );
        if (hour != adjustedHour)
            return localDateTime.plusDays(1);
        return localDateTime;
    }

    public int getStartTimeValue() {
        return startTime.getValue();
    }

    public int getEndTimeValue() {
        return endTime.getValue();
    }

    public String toString() {
        return String.format(
                "%04d%02d%02d %02d:%02d-%02d:%02d",
                workingDate.getYear().getYear(),
                workingDate.getMonth().getMonth(),
                workingDate.getDay().getDay(),
                startTime.getHour().getHour(),
                startTime.getMinute().getMinute(),
                endTime.getHour().getHour(),
                endTime.getMinute().getMinute()
        );
    }
}
