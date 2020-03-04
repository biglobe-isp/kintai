package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface IAttendanceFactory {
    Attendance createForRegister(WorkDay workDay, VerifiedAttendanceTime attendanceTime, WorkRegulations workRegulations);

    Attendance createFromCsv(WorkDay workDay, VerifiedAttendanceTime attendanceTime, TimeUnit workingHours, TimeUnit overTimeHours);
}
