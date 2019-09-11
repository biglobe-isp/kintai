package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.rules.BreakTimeRule;
import com.naosim.dddwork.domain.rules.RegularWorkingDurationRule;
import lombok.Getter;

import java.time.Duration;

public class AttendanceSummary {
    @Getter
    private Duration regularTime = null;
    @Getter
    private Duration overTime = null;
    private static BreakTimeRule breakTimeRules = new BreakTimeRule();
    private static RegularWorkingDurationRule regularWorkingDurationRule = new RegularWorkingDurationRule();

    AttendanceSummary get(AttendanceRecords attendanceRecords) {

        long regularWorkingMinutes = attendanceRecords.getAttendanceRecords()
                .map(r -> breakTimeRules.calculateTotalWorkingHours(r))
                .map(r -> regularWorkingDurationRule.calculateRegularWorkingHours(r).toMinutes())
                .sum()
                .longValue();

        long overtimeWorkingMinutes = attendanceRecords.getAttendanceRecords()
                .map(r -> breakTimeRules.calculateTotalWorkingHours(r))
                .map(r -> regularWorkingDurationRule.calculateOvertimeWorkingHours(r).toMinutes())
                .sum()
                .longValue();

        regularTime = Duration.ofMinutes(regularWorkingMinutes);
        overTime = Duration.ofMinutes(overtimeWorkingMinutes);
        return this;
    }

    public long getRegularWorkingHours() {
        return regularTime.toHours();
    }

    public long getRegularWorkingMinutes() {
        return regularTime.toMinutes() % 60;
    }

    public long getOverTimeWorkingHours() {
        return overTime.toHours();
    }

    public long getOverTimWorkingMinutes() {
        return overTime.toMinutes() % 60;
    }

    public String toString() {
        return regularTime.toHours() + ":" + regularTime.toMinutes() % 60 + "/" + overTime.toHours() + ":" + overTime.toMinutes() % 60;
    }
}
