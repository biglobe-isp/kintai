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
            this.yearMonth = YearMonth.parse(yyyymm, DateTimeFormatter.ofPattern("yyyyMM"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("不正な年月です。");
        }
    }
}
