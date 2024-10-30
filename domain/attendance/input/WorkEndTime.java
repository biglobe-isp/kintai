package com.naosim.dddwork.domain.attendance.input;

import java.time.LocalTime;

public class WorkEndTime {
    private final LocalTime value;

    public WorkEndTime(LocalTime value) {
        this.value = value;
    }

    public LocalTime getValue() {
        return value;
    }
}
