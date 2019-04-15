package com.naosim.dddwork.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class AttendanceFactory {

    public Attendance create(
            LocalDate date,
            TimePoint startTime,
            TimePoint endTime,
            WorkMinute workMinute,
            WorkMinute overWorkMinute,
            LocalDateTime createAt
    ) {
        return new Attendance(
                date,
                startTime,
                endTime,
                workMinute,
                overWorkMinute,
                createAt
        );
    }
}
