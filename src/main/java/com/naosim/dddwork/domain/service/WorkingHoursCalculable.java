package com.naosim.dddwork.domain.service;

import com.naosim.dddwork.domain.attendance.AttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkingHours;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface WorkingHoursCalculable {
    WorkingHours calcWorkingHours(
            AttendanceTime attendanceTime, WorkRegulations workRegulations);
}
