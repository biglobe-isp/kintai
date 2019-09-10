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
    private BreakTimeRule breakTimeRule;

    BreakTimes(int startHour, int startMinute, int endHour, int endMinute) {
        EntryTime startTime = new EntryTime(new Hour(startHour), new Minute(startMinute));
        EntryTime endTime = new EntryTime(new Hour(endHour), new Minute(endMinute));
        breakTimeRule = new BreakTimeRule(startTime, endTime);
    }

    public BreakTimeRule getBreakTimeRule() {
        return breakTimeRule;
    }
}

public class BreakTimeRules {
    public Duration calculateTotalWorkingHours(AttendanceRecord attendanceRecord) {

        LocalDateTime startTime = BreakTimeRule.convertToLocalDateTime(
                attendanceRecord.getWorkingDate().getYear().getYear(),
                attendanceRecord.getWorkingDate().getMonth().getMonth(),
                attendanceRecord.getWorkingDate().getDay().getDay(),
                attendanceRecord.getWorkingDuration().getStartTime()
        );
        LocalDateTime endTime = BreakTimeRule.convertToLocalDateTime(
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
                .map(BreakTimes::getBreakTimeRule)
                .map(rule -> getBreakDuration(startTime, endTime, rule))
                .filter(Objects::nonNull).mapToLong(Duration::toMinutes).sum();

        return Duration.ofMinutes(breakMinutes);
    }

    private Duration getBreakDuration(
            LocalDateTime startTime,
            LocalDateTime endTime,
            BreakTimeRule breakTimeRule) {

        LocalDateTime breakStartTime = breakTimeRule.getBreakTimeFrom(startTime);
        LocalDateTime breakEndTime = breakTimeRule.getBreakTimeTo(startTime);

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

