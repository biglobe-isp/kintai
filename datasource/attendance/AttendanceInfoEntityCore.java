package com.naosim.dddwork.datasource.attendance;

import com.naosim.dddwork.domain.attendance.input.AttendanceInfoEntity;
import com.naosim.dddwork.domain.attendance.input.WorkDate;
import com.naosim.dddwork.domain.attendance.input.WorkEndTime;
import com.naosim.dddwork.domain.attendance.input.WorkStartTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceInfoEntityCore {
    public LocalDate workDate;
    public LocalTime startTime;
    public LocalTime endTime;
    public Integer workMinutes;
    public Integer overWorkMinutes;
    public LocalDateTime now;

    public AttendanceInfoEntityCore(
            LocalDate workDate,
            LocalTime startTime,
            LocalTime endTime,
            Integer workMinutes,
            Integer overWorkMinutes,
            LocalDateTime now) {
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workMinutes = workMinutes;
        this.overWorkMinutes = overWorkMinutes;
        this.now = now;
    }

    public AttendanceInfoEntity create() throws Exception {
        return new AttendanceInfoEntity(new WorkDate(workDate), new WorkStartTime(startTime), new WorkEndTime(endTime));
    }
}
