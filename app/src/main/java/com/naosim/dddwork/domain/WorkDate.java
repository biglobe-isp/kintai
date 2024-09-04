package com.naosim.dddwork.domain;

import java.time.LocalDate;

class WorkDate {
    LocalDate yearMonth;

    WorkDate(int year, int month, int day) {
        yearMonth = LocalDate.of(year, month, day);
    }
}