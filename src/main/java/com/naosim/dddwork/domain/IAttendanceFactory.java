package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.AttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface IAttendanceFactory {
    Attendance createForRegister(WorkDay workDay, AttendanceTime attendanceTime, WorkRegulations workRegulations);

    Attendance createFromCsv(WorkDay workDay, AttendanceTime attendanceTime, TimeUnit workingHours, TimeUnit overTimeHours);
}
