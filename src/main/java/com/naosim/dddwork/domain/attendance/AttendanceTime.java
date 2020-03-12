package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.api.attendanceTime.VerifiedAttendanceTime;
import lombok.Value;

@Value
public class AttendanceTime {
    StartTime startTime;
    EndTime endTime;

    public static AttendanceTime of(VerifiedAttendanceTime verifiedAttendanceTime) {
        return new AttendanceTime(verifiedAttendanceTime.getStartTime(), verifiedAttendanceTime.getEndTime());
    }

    private AttendanceTime(StartTime startTime, EndTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
