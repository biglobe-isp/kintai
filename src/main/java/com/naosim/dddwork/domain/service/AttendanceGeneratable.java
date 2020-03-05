package com.naosim.dddwork.domain.service;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.OverTimeHours;
import com.naosim.dddwork.domain.attendance.WorkingHours;
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface AttendanceGeneratable {
    Attendance createForRegister(WorkDay workDay, VerifiedAttendanceTime attendanceTime, WorkRegulations workRegulations);

    Attendance createFromCsv(WorkDay workDay, VerifiedAttendanceTime attendanceTime, WorkingHours workingHours, OverTimeHours overTimeHours);
}
