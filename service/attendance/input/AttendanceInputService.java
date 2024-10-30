package com.naosim.dddwork.service.attendance.input;

import com.naosim.dddwork.domain.attendance.AttendanceService;
import com.naosim.dddwork.domain.attendance.input.WorkDate;
import com.naosim.dddwork.domain.attendance.input.WorkEndTime;
import com.naosim.dddwork.domain.attendance.input.WorkStartTime;

public class AttendanceInputService {
    private final AttendanceService attendanceService = new AttendanceService();

    public void input(WorkDate date, WorkStartTime workStartTime, WorkEndTime workEndTime) throws Exception {
        attendanceService.input(date, workStartTime, workEndTime);
    }
}
