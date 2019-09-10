package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.EntryTime;

import java.time.LocalDateTime;

class BreakTimeRule {
    private final EntryTime startTime;
    private final EntryTime endTime;

    BreakTimeRule(EntryTime startTime, EntryTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    LocalDateTime getBreakTimeFrom(LocalDateTime startLocalDateTime) {
        return convertToLocalDateTime(
                startLocalDateTime.getYear(),
                startLocalDateTime.getMonthValue(),
                startLocalDateTime.getDayOfMonth(),
                startTime
        );
    }

    LocalDateTime getBreakTimeTo(LocalDateTime startLocalDateTime) {
        return convertToLocalDateTime(
                startLocalDateTime.getYear(),
                startLocalDateTime.getMonthValue(),
                startLocalDateTime.getDayOfMonth(),
                endTime
        );
    }

    static LocalDateTime convertToLocalDateTime(int year, int month, int day, EntryTime entryTime) {
        // if hour > 24 , adjust date to the next day
        // LocalDateTime hour allows from 0 to 23
        final int MAXIMUM_HOUR_VALUE = 24;
        int hour = entryTime.getHour().getHour();
        int adjustedHour = hour >= MAXIMUM_HOUR_VALUE ? hour - MAXIMUM_HOUR_VALUE : hour;
        LocalDateTime localDateTime = LocalDateTime.of(
                year,
                month,
                day,
                adjustedHour,
                entryTime.getMinute().getMinute()
        );
        if (hour != adjustedHour)
            return localDateTime.plusDays(1);
        return localDateTime;
    }
}
