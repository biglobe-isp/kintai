package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.rules.FireConditionRule;
import io.vavr.control.Either;

public class AttendanceSummaryCalculator {
    private AttendanceSummaryFailed attendanceSummaryFailed;
    private final FireConditionRule fireConditionRule = new FireConditionRule();

    public Either<AttendanceSummaryFailed, AttendanceSummary> calculate(AttendanceRecords attendanceRecords) {

        boolean fired = fireConditionRule.isEmployeeFired(attendanceRecords);
        if (fired)
            return Either.left(attendanceSummaryFailed);
        else
            return Either.right(new AttendanceSummary().get(attendanceRecords));
    }
}
