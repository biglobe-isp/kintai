package com.naosim.dddwork.domain;

import lombok.Value;

@Value
public class YearMonth {

    private final int year;
    private final int month;

    public static YearMonth of(int year, int month) {
        return new YearMonth(year, month);
    }
}
