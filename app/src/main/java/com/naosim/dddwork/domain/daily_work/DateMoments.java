package com.naosim.dddwork.domain.daily_work;

import java.time.LocalDate;

/**
 * 年月日
 */
public class DateMoments {
    private final LocalDate date;

    DateMoments(int year, int month, int day) {
        date = LocalDate.of(year, month, day);
    }

    DateMoments(DateMoments workDate) {
        this.date = workDate.date;
    }

    public LocalDate getDate() {
        return date;
    }
}