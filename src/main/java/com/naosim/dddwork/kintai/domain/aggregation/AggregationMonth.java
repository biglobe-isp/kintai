package com.naosim.dddwork.kintai.domain.aggregation;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;
import java.time.YearMonth;

@Value
public class AggregationMonth {
    @NonNull
    YearMonth yearMonth;

    public boolean equalsYearMonth(LocalDate date) {
        return equalsYear(date) && equalsMonth(date);
    }

    private boolean equalsYear(LocalDate date) {
        return this.yearMonth.getYear() == date.getYear();
    }

    private boolean equalsMonth(LocalDate date) {
        return this.yearMonth.getMonth() == date.getMonth();
    }
}
