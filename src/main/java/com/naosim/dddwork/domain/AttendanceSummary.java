package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.rules.BreakTimeRule;
import com.naosim.dddwork.domain.rules.BreakTimeRules;
import com.naosim.dddwork.domain.rules.OverTimeRule;
import com.naosim.dddwork.domain.rules.RegularTimeRule;
import com.naosim.dddwork.domain.time.EntryTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.time.Duration.between;
import static java.time.Duration.ofMinutes;

@NoArgsConstructor
public class AttendanceSummary {
    @Getter
    private Duration regularTime = null;
    @Getter
    private Duration overTime = null;
    @Getter
    private boolean fired;
    private static int regularWorkingMinutes = RegularTimeRule.REGULAR_WORKING_DURATION.getEntryTime()
            .getHour()
            .getHour() * 60
            + RegularTimeRule.REGULAR_WORKING_DURATION.getEntryTime().getMinute().getMinute();

    public AttendanceSummary(AttendanceRecords attendanceRecords) {
        regularTime = ofMinutes(0);
        overTime = ofMinutes(0);
        fired = false;

        // calculate summary by records
        for (AttendanceRecord attendanceRecord : attendanceRecords.getAttendanceRecords()) {

            fired = checkFired(attendanceRecord);
            if (fired) {
                return; // stop calculating the summary
            }
            // add working time to private variable regularTime and overTime
            Duration totalWorkingHours = calculateTotalWorkingHours(attendanceRecord);

            Duration regularWorkingHours = calculateRegularWorkingHours(totalWorkingHours);
            regularTime = regularTime.plus(regularWorkingHours);

            Duration overtimeWorkingHours = calculateOvertimeWorkingHours(totalWorkingHours);
            overTime = overTime.plus(overtimeWorkingHours);
        }
    }

    public boolean checkFired(AttendanceRecord attendanceRecord) {
        EntryTime startTime = attendanceRecord.getWorkingDuration().getStartTime();
        EntryTime regularStatTime = RegularTimeRule.REGULAR_TIME_START.getEntryTime();

        return startTime.getValue() > regularStatTime.getValue() ? true : false;
    }

    Duration calculateTotalWorkingHours(AttendanceRecord attendanceRecord) {

        // get startTime & endTime
        EntryTime startTime = attendanceRecord.getWorkingDuration().getStartTime();
        EntryTime endTime = attendanceRecord.getWorkingDuration().getEndTime();

        // calculate working hours
        Duration workingHours = calculateDuration(attendanceRecord.getWorkingDate(), startTime, endTime);

        long breakMinutes = Arrays.stream(BreakTimeRules.values()).
                map(r -> r.getBreakTimeRule()).
                map(rule -> getBreakDuration(attendanceRecord.getWorkingDate(), endTime, rule)).
                filter(Objects::nonNull).
                collect(Collectors.summingLong(d -> d.toMinutes()));

        workingHours = workingHours.minus(ofMinutes(breakMinutes));
        return workingHours;
    }

    Duration getBreakDuration(WorkingDate workingDate, EntryTime endTime, BreakTimeRule breakTimeRule) {

        return endTime.getValue() >= breakTimeRule.getEndTime().getValue() ?
                calculateDuration(workingDate, breakTimeRule.getStartTime(), breakTimeRule.getEndTime()) :
                (endTime.getValue() >= breakTimeRule.getStartTime().getValue() &&
                        endTime.getValue() < breakTimeRule.getEndTime().getValue() ?
                        calculateDuration(workingDate, breakTimeRule.getStartTime(), endTime) : null);
    }

    Duration calculateRegularWorkingHours(Duration totalWorkingHours) {
        return totalWorkingHours.toMinutes() > regularWorkingMinutes ?
                ofMinutes(regularWorkingMinutes) : totalWorkingHours;
    }

    Duration calculateOvertimeWorkingHours(Duration totalWorkingHours) {
        return totalWorkingHours.toMinutes() > regularWorkingMinutes ?
                totalWorkingHours.minus(ofMinutes(regularWorkingMinutes)) : ofMinutes(0);
    }

    Duration calculateDuration(WorkingDate workingDate, EntryTime startTime, EntryTime endTime) {

        LocalDateTime from = LocalDateTime.of(workingDate.getYear().getYear(),
                                              workingDate.getMonth().getMonth(),
                                              workingDate.getDay().getDay(),
                                              startTime.getHour().getHour(),
                                              startTime.getMinute().getMinute()
        );

        return endTime.getValue() >= OverTimeRule.FINAL_CUT_TIME.getEntryTime().getValue() ?
                calculateDurationOverNight(from, startTime) :
                between(from, LocalDateTime.of(workingDate.getYear().getYear(),
                                               workingDate.getMonth().getMonth(),
                                               workingDate.getDay().getDay(),
                                               endTime.getHour().getHour(),
                                               endTime.getMinute().getMinute()
                ));
    }

    Duration calculateDurationOverNight(LocalDateTime from, EntryTime startTime) {
        LocalDateTime to = from.plusDays(1).minusHours(startTime.getHour().getHour());
        return between(from, to);
    }

    public String toString() {
        return regularTime.toHours() + ":" + regularTime.toMinutes() % 60 + "/" + overTime.toHours() + ":" + overTime.toMinutes() % 60;
    }
}