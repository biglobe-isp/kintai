package com.naosim.dddwork.kintai.controller;

import lombok.NonNull;
import lombok.Value;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Value
class AggregationMonthInputParameter {
    YearMonth yearMonth;

    public AggregationMonthInputParameter(@NonNull String yyyymm) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
            this.yearMonth = YearMonth.parse(yyyymm, dtf);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("不正な年月です。");
        }
    }
}
