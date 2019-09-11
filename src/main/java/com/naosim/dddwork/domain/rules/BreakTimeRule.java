package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static java.time.Duration.between;

enum BreakTimes {
    LUNCH_BREAK_TIME(12, 0, 13, 0),
    EVENING_BREAK(18, 0, 19, 0),
    LATE_NIGHT_BREAK(21, 0, 22, 0);
    private EntryTime startTime;
    private EntryTime endTime;

    BreakTimes(int startHour, int startMinute, int endHour, int endMinute) {
        startTime = new EntryTime(new Hour(startHour), new Minute(startMinute));
        endTime = new EntryTime(new Hour(endHour), new Minute(endMinute));
    }

    public EntryTime getStartTime() {
        return startTime;
    }

    public EntryTime getEndTime() {
        return endTime;
    }
}

public class BreakTimeRule {
    public Duration calculateTotalWorkingHours(AttendanceRecord attendanceRecord) {

        LocalDateTime startTime = convertToLocalDateTime(
                attendanceRecord.getWorkingDate().getYear().getYear(),
                attendanceRecord.getWorkingDate().getMonth().getMonth(),
                attendanceRecord.getWorkingDate().getDay().getDay(),
                attendanceRecord.getWorkingDuration().getStartTime()
        );
        LocalDateTime endTime = convertToLocalDateTime(
                attendanceRecord.getWorkingDate().getYear().getYear(),
                attendanceRecord.getWorkingDate().getMonth().getMonth(),
                attendanceRecord.getWorkingDate().getDay().getDay(),
                attendanceRecord.getWorkingDuration().getEndTime()
        );

        Duration totalDuration = between(startTime, endTime);
        Duration totalBreakDuration = calculateTotalBreakDuration(startTime, endTime);
        return totalDuration.minus(totalBreakDuration);
    }

    private Duration calculateTotalBreakDuration(LocalDateTime startTime, LocalDateTime endTime) {
        long breakMinutes = Arrays.stream(BreakTimes.values())
                .map(t -> getBreakDuration(startTime, endTime, t.getStartTime(), t.getEndTime()))
                .filter(Objects::nonNull).mapToLong(Duration::toMinutes).sum();

        return Duration.ofMinutes(breakMinutes);
    }

    private Duration getBreakDuration(
            LocalDateTime startTime,
            LocalDateTime endTime,
            EntryTime breakStartEntryTime,
            EntryTime breakEndEntryTime) {

        LocalDateTime breakStartTime = convertToLocalDateTime(
                startTime.getYear(),
                startTime.getMonthValue(),
                startTime.getDayOfMonth(),
                breakStartEntryTime
        );
        LocalDateTime breakEndTime = convertToLocalDateTime(
                startTime.getYear(),
                startTime.getMonthValue(),
                startTime.getDayOfMonth(),
                breakEndEntryTime
        );

        if (breakStartTime.isAfter(endTime) || breakStartTime.isEqual(endTime))
            return Duration.ofMinutes(0);

        return breakStartTime.isAfter(startTime) && breakEndTime.isBefore(endTime) ? between(
                breakStartTime,
                breakEndTime
        ) : between(
                breakStartTime,
                endTime
        );
    }

    LocalDateTime convertToLocalDateTime(int year, int month, int day, EntryTime entryTime) {
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

