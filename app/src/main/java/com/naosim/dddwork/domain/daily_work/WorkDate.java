package com.naosim.dddwork.domain.daily_work;

import java.time.LocalDate;

public class WorkDate {
    private final LocalDate yearMonth;

    WorkDate(int year, int month, int day) {
        yearMonth = LocalDate.of(year, month, day);
    }

    WorkDate(WorkDate workDate) {
        this.yearMonth = workDate.yearMonth;
    }

    public LocalDate getYearMonth() {
        return yearMonth;
    }
}