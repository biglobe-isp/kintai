package com.naosim.dddwork.domain.service;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.OverTimeHours;
import com.naosim.dddwork.domain.attendance.WorkingHours;
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;

public interface AttendanceGeneratableForRepository {
    Attendance create(WorkDay workDay,
                      VerifiedAttendanceTime attendanceTime,
                      WorkingHours workingHours,
                      OverTimeHours overTimeHours);
}
