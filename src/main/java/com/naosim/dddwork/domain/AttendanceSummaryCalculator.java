package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.rules.RegularTimeRule;
import io.vavr.control.Either;
import lombok.Getter;

public class AttendanceSummaryCalculator {
    @Getter
    private static boolean noDelayCheck;

    public static Either<Fired, AttendanceSummary> calculate(AttendanceRecords attendanceRecords) {

        // if employee is late in the office even in one time, simply fire this guy
        noDelayCheck = attendanceRecords.getAttendanceRecords()
                .toJavaStream()
                .allMatch(r -> r.getWorkingDuration()
                        .getStartTime()
                        .getValue() <= RegularTimeRule.REGULAR_TIME_START.getEntryTime().getValue());
        if (!noDelayCheck)
            return Either.left(new Fired());
        else
            return Either.right(new AttendanceSummary().get(attendanceRecords));
    }
}
