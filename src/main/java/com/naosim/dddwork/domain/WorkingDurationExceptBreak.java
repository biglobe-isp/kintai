package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.rules.BreakTimes;
import com.naosim.dddwork.domain.time.EntryTime;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static java.time.Duration.between;

public class WorkingDurationExceptBreak {
    @Getter
    private Duration workingDurationExceptBreak;

    public WorkingDurationExceptBreak(AttendanceRecord attendanceRecord) {

        TotalWorkingDuration totalWorkingDuration = new TotalWorkingDuration(attendanceRecord);
        Duration totalBreakDuration = calculateTotalBreakDuration(
                attendanceRecord.getStartLocalDateTime(),
                attendanceRecord.getEndLocalDateTime()
        );
        workingDurationExceptBreak = totalWorkingDuration.getTotalWorkingDuration().minus(totalBreakDuration);
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

        LocalDateTime breakStartTime = LocalDateTime.of(
                startTime.getYear(),
                startTime.getMonth(),
                startTime.getDayOfMonth(),
                breakStartEntryTime.getHour().getHour(),
                breakStartEntryTime.getMinute().getMinute()
        );

        LocalDateTime breakEndTime = LocalDateTime.of(
                startTime.getYear(),
                startTime.getMonth(),
                startTime.getDayOfMonth(),
                breakEndEntryTime.getHour().getHour(),
                breakEndEntryTime.getMinute().getMinute()
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
}
