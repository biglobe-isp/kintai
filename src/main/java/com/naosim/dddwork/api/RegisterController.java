package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.attendance.attendancetime.NotVerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final AttendanceService attendanceService;

    public void register(WorkDay workDay, NotVerifiedAttendanceTime notVerifiedAttendanceTime) {
        if (notVerifiedAttendanceTime.isStartGreaterThanEnd()) {
            throw new RuntimeException("開始時刻＞終了時刻は登録できません");
        }
        VerifiedAttendanceTime attendanceTime = VerifiedAttendanceTime.of(notVerifiedAttendanceTime.getStartTime(),
                                                                          notVerifiedAttendanceTime.getEndTime());
        attendanceService.registerAttendance(workDay, attendanceTime);
    }
}
