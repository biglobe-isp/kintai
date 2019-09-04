package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.rules.BreakTimeRule;
import com.naosim.dddwork.domain.rules.BreakTimeRules;
import com.naosim.dddwork.domain.rules.OverTimeRule;
import com.naosim.dddwork.domain.rules.RegularTimeRule;
import com.naosim.dddwork.domain.time.EntryTime;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static java.time.Duration.between;

public class AttendanceSummary {
    @Getter
    private Duration regularTime = null;
    @Getter
    private Duration overTime = null;
    @Getter
    private final static boolean fired = false;
    private static int regularWorkingMinutes = RegularTimeRule.REGULAR_WORKING_DURATION.getEntryTime()
            .getHour()
            .getHour() * 60 + RegularTimeRule.REGULAR_WORKING_DURATION.getEntryTime().getMinute().getMinute();

    AttendanceSummary get(AttendanceRecords attendanceRecords) {

        regularTime = Duration.ofMinutes(0);
        overTime = Duration.ofMinutes(0);

        for (AttendanceRecord attendanceRecord : attendanceRecords.getAttendanceRecords()) {

            // add working time to private variable regularTime and overTime
            Duration totalWorkingHours;
            totalWorkingHours = calculateTotalWorkingHours(attendanceRecord);

            Duration regularWorkingHours = calculateRegularWorkingHours(totalWorkingHours);
            regularTime = regularTime.plus(regularWorkingHours);

            Duration overtimeWorkingHours = calculateOvertimeWorkingHours(totalWorkingHours);
            overTime = overTime.plus(overtimeWorkingHours);
        }

        return this;
    }

    //@VisibleForTesting
    private Duration calculateTotalWorkingHours(AttendanceRecord attendanceRecord) {

        EntryTime startTime = attendanceRecord.getWorkingDuration().getStartTime();
        EntryTime endTime = attendanceRecord.getWorkingDuration().getEndTime();

        Duration workingHours = calculateDuration(attendanceRecord.getWorkingDate(), startTime, endTime);

        long breakMinutes = Arrays.stream(BreakTimeRules.values())
                .map(BreakTimeRules::getBreakTimeRule)
                .map(rule -> getBreakDuration(attendanceRecord.getWorkingDate(), endTime, rule))
                .filter(Objects::nonNull).mapToLong(Duration::toMinutes).sum();

        return workingHours.minus(Duration.ofMinutes(breakMinutes));
    }

    private Duration getBreakDuration(WorkingDate workingDate, EntryTime endTime, BreakTimeRule breakTimeRule) {

        return endTime.getValue() >= breakTimeRule.getEndTime().getValue() ?
                calculateDuration(workingDate, breakTimeRule.getStartTime(), breakTimeRule.getEndTime()) :
                (endTime.getValue() >= breakTimeRule.getStartTime().getValue() &&
                        endTime.getValue() < breakTimeRule.getEndTime().getValue() ?
                        calculateDuration(workingDate, breakTimeRule.getStartTime(), endTime) : null);
    }

    private Duration calculateRegularWorkingHours(Duration totalWorkingHours) {
        return totalWorkingHours.toMinutes() > regularWorkingMinutes ?
                Duration.ofMinutes(regularWorkingMinutes) : totalWorkingHours;
    }

    private Duration calculateOvertimeWorkingHours(Duration totalWorkingHours) {
        return totalWorkingHours.toMinutes() > regularWorkingMinutes ?
                totalWorkingHours.minus(Duration.ofMinutes(regularWorkingMinutes)) : Duration.ofMinutes(0);
    }

    private Duration calculateDuration(WorkingDate workingDate, EntryTime startTime, EntryTime endTime) {

        LocalDateTime from = LocalDateTime.of(
                workingDate.getYear().getYear(),
                workingDate.getMonth().getMonth(),
                workingDate.getDay().getDay(),
                startTime.getHour().getHour(),
                startTime.getMinute().getMinute()
        );

        return endTime.getValue() >= OverTimeRule.FINAL_CUT_TIME.getEntryTime().getValue() ?
                calculateDurationOverNight(from, startTime) :
                between(from, LocalDateTime.of(
                        workingDate.getYear().getYear(),
                        workingDate.getMonth().getMonth(),
                        workingDate.getDay().getDay(),
                        endTime.getHour().getHour(),
                        endTime.getMinute().getMinute()
                ));
    }

    private Duration calculateDurationOverNight(LocalDateTime from, EntryTime startTime) {
        LocalDateTime to = from.plusDays(1).minusHours(startTime.getHour().getHour());
        return between(from, to);
    }

    public String getSummaryMessage() {
        return "Regular Working Hours = " + regularTime.toHours() + ":" + regularTime.toMinutes() % 60 +
                " Over Time Working Hours = " + overTime.toHours() + ":" + overTime.toMinutes() % 60;
    }

    public String toString() {
        return regularTime.toHours() + ":" + regularTime.toMinutes() % 60 + "/" + overTime.toHours() + ":" + overTime.toMinutes() % 60;
    }
}
