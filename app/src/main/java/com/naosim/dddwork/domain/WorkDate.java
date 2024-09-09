package com.naosim.dddwork.domain;

import java.time.LocalDate;

class WorkDate {
    private final LocalDate yearMonth;

    WorkDate(int year, int month, int day) {
        yearMonth = LocalDate.of(year, month, day);
    }

    WorkDate(WorkDate workDate) {
        this.yearMonth = workDate.yearMonth;
    }

    LocalDate getYearMonth() {
        return yearMonth;
    }
}