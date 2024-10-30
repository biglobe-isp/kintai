package com.naosim.dddwork.service.attendance.total;

import com.naosim.dddwork.domain.attendance.AttendanceService;
import com.naosim.dddwork.domain.attendance.total.AttendanceTotalData;

public class AttendanceTotalService {
    private final AttendanceService attendanceService = new AttendanceService();

    public AttendanceTotalData getMonthlyTotal() throws Exception {
        return attendanceService.getMonthlyTotal();
    }
}
