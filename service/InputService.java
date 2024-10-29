package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.service.AttendanceService;
import com.naosim.dddwork.domain.value_object.Date;
import com.naosim.dddwork.domain.value_object.WorkEndTime;
import com.naosim.dddwork.domain.value_object.WorkStartTime;

import java.time.LocalDate;
import java.time.LocalTime;

public class InputService {
    private final AttendanceService attendanceService = new AttendanceService();

    public void input(LocalDate date, LocalTime startTime, LocalTime endTime) throws Exception {
        attendanceService.input(new Date(date), new WorkStartTime(startTime), new WorkEndTime(endTime));
    }
}
