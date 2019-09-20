package com.naosim.dddwork.domain;

import lombok.Getter;

import java.time.Duration;

public class TotalWorkingDuration {
    @Getter
    private Duration totalWorkingDuration;

    public TotalWorkingDuration(AttendanceRecord attendanceRecord) {
        totalWorkingDuration = Duration.between(
                attendanceRecord.getStartLocalDateTime(),
                attendanceRecord.getEndLocalDateTime()
        );
    }
}
