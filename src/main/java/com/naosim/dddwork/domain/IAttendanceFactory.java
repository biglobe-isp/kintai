package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.AttendanceTime;

import java.time.LocalDate;

public interface IAttendanceFactory {
    Attendance createForRegister(LocalDate workD, AttendanceTime attendanceTime);

    Attendance createFromCsv(String workD, String start, String end, String workingH, String overTimeH);
}
