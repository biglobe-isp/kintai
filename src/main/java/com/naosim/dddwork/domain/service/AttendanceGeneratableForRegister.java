package com.naosim.dddwork.domain.service;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.AttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface AttendanceGeneratableForRegister {
    Attendance create(
            WorkDay workDay,
            AttendanceTime attendanceTime,
            WorkRegulations workRegulations);
}
