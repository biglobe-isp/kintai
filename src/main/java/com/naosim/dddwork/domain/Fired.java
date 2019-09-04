package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.rules.RegularTimeRule;
import io.vavr.control.Either;
import lombok.Getter;

public class Fired {

    @Getter
    private boolean fired;
    public Either<Fired, AttendanceSummary> get(AttendanceRecords attendanceRecords) {
        AttendanceSummary result;

        // if employee is late in the office even in one time, simply fire this guy
        fired = attendanceRecords.getAttendanceRecords()
                .toJavaStream()
                .allMatch(r -> r.getWorkingDuration()
                        .getStartTime()
                        .getValue() > RegularTimeRule.REGULAR_TIME_START.getEntryTime().getValue());


        if (fired) {
            return Either.left(this);
        } else result = new AttendanceSummary().get(attendanceRecords);
        return Either.right(result);
    }

    public String firedMessage()
    {
        return "You are FIRED!!!!";
    }


}
