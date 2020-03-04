package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.attendance.NotVerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.service.AttendanceService;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController {
    private final AttendanceService attendanceService;

    public RegisterController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    public void register(WorkDay workDay, NotVerifiedAttendanceTime notVerifiedAttendanceTime) {
        if (notVerifiedAttendanceTime.isStartGreaterThanEnd()) {
            throw new RuntimeException("開始時刻＞終了時刻は登録できません");
        }
        VerifiedAttendanceTime attendanceTime = VerifiedAttendanceTime.of(notVerifiedAttendanceTime.getStartTime(),
                                                                          notVerifiedAttendanceTime.getEndTime());
        attendanceService.registerAttendance(workDay, attendanceTime);
    }
}
