package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.AttendanceRecords;

public class FireConditionRule {
    private static final int MUST_ARRIVAL_TIME_VALUE = 900; // AM 09:00

    public boolean isEmployeeFired(AttendanceRecords attendanceRecords) {

        return !attendanceRecords.getAttendanceRecords()
                .forAll(r -> r.getWorkingDuration().getStartTime().getValue() <= MUST_ARRIVAL_TIME_VALUE);
    }
}
