package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.rules.RegularTimeRule;
import io.vavr.control.Either;

public class AttendanceSummaryCalculator {
    private static AttendanceSummaryFailed attendanceSummaryFailed;

    public static Either<AttendanceSummaryFailed, AttendanceSummary> calculate(AttendanceRecords attendanceRecords) {

        // if employee is late in the office even in one time, simply fire this guy
        boolean noDelay = attendanceRecords.getAttendanceRecords()
                .toJavaStream()
                .allMatch(r -> r.getWorkingDuration()
                        .getStartTime()
                        .getValue() <= RegularTimeRule.getRegularStartTime().getValue());
        if (!noDelay)
            return Either.left(attendanceSummaryFailed);
        else
            return Either.right(new AttendanceSummary().get(attendanceRecords));
    }
}
