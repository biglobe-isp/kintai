package com.naosim.dddwork.domain.attendance.input;

import java.time.LocalTime;

public class WorkStartTime {
    private static final LocalTime FORMAL_WORK_START_TIME = LocalTime.of(9, 0);
    private final LocalTime value;

    public WorkStartTime(LocalTime value) throws Exception {
        if (value.isAfter(FORMAL_WORK_START_TIME)) {
            throw new Exception("遅刻はNGです。");
        }
        this.value = value;
    }

    public LocalTime getValue() {
        return value;
    }
}
