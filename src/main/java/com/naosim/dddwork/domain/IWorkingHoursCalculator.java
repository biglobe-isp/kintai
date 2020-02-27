package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.attendance.AttendanceTime;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface IWorkingHoursCalculator {
    TimeUnit calcWorkingHours(
            AttendanceTime attendanceTime, WorkRegulations workRegulations);
}
