package com.naosim.dddwork.domain.service;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.AttendanceTime;
import com.naosim.dddwork.domain.attendance.OverTimeHours;
import com.naosim.dddwork.domain.attendance.WorkingHours;
import com.naosim.dddwork.domain.attendance.WorkDay;

public interface AttendanceGeneratableForRepository {
    Attendance create(WorkDay workDay,
                      AttendanceTime attendanceTime,
                      WorkingHours workingHours,
                      OverTimeHours overTimeHours);
}
