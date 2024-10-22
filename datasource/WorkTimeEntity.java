package com.naosim.dddwork.datasource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class WorkTimeEntity {
    public LocalDate date;
    public LocalTime startTime;
    public LocalTime endTime;
    public Integer workMinutes;
    public Integer overWorkMinutes;
    public LocalDateTime now;
    public WorkTimeEntity(LocalDate date, LocalTime startTime, LocalTime endTime, Integer workMinutes, Integer overWorkMinutes, LocalDateTime now) {}
}
