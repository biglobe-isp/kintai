package com.naosim.dddwork.domain.daily_work;

import java.time.LocalDate;

/**
 * 集計対象年月
 */
public class AggregationYearMonth {
    private final LocalDate yearMonth;

    public AggregationYearMonth(LocalDate yearMonth) {
        this.yearMonth = yearMonth;
    }

    public LocalDate getValue() {
        return yearMonth;
    }
}