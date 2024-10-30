package com.naosim.dddwork.domain.attendance.total;

import java.time.LocalDate;

public class WorkMonth {
    private final LocalDate value;

    public WorkMonth(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
