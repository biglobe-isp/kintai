package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
import org.springframework.stereotype.Component;

@Component
public class AttendanceFactory {

    public Attendance create(WorkDay workDay,
                             VerifiedAttendanceTime attendanceTime,
                             WorkingHours workingHours,
                             OverTimeHours overTimeHours) {
        return new Attendance(workDay, attendanceTime, workingHours, overTimeHours);
    }
}
