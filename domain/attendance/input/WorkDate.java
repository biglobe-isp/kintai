package com.naosim.dddwork.domain.attendance.input;

import java.time.LocalDate;

public class WorkDate {
    private final LocalDate value;

    public WorkDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
