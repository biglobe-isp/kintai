package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkTimeOfDay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
class AttendanceFactory {

    private final Clock clock;

    Attendance create(
            LocalDate date,
            TimePoint startTime,
            TimePoint endTime,
            WorkTimeOfDay workTimeOfDay) {
        return new Attendance(
                date,
                startTime,
                endTime,
                workTimeOfDay.getWorkMinute(),
                workTimeOfDay.getOverWorkMinute(),
                LocalDate.now(clock)
        );
    }
}
