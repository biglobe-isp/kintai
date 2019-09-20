package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.rules.FireConditionRule;
import io.vavr.control.Either;

public class AttendanceSummaryCalculator {
    public Either<AttendanceSummaryFailed, AttendanceSummary> calculate(AttendanceRecords attendanceRecords) {

        FireConditionRule fireConditionRule = new FireConditionRule();
        boolean fired = fireConditionRule.isEmployeeFired(attendanceRecords);
        if (fired) {
            return Either.left(AttendanceSummaryFailed.FIRED);
        } else {
            return Either.right(new AttendanceSummary(attendanceRecords));
        }
    }
}
