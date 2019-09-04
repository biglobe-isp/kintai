package com.naosim.dddwork.domain.date;

import lombok.Getter;

public class YearMonth {
    @Getter
    private final Year year;
    @Getter
    private final Month month;

    public YearMonth(Year year, Month month) {
        this.year = year;
        this.month = month;
    }

    public int getValue() {
        return year.getYear() * 100 + month.getMonth();
    }
}
