package com.naosim.dddwork.api.attendance.input;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceInputRequest {
    public LocalDate date;
    public LocalTime startTime;
    public LocalTime endTime;

    public AttendanceInputRequest(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
