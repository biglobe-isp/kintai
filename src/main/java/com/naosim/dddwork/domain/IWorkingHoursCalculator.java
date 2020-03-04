package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.attendance.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface IWorkingHoursCalculator {
    TimeUnit calcWorkingHours(
            VerifiedAttendanceTime attendanceTime, WorkRegulations workRegulations);
}
