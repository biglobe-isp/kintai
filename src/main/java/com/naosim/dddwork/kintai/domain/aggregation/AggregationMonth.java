package com.naosim.dddwork.kintai.domain.aggregation;

import lombok.Value;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Value
public class AggregationMonth {
    YearMonth yearMonth;

    public AggregationMonth(String yyyymm) {
        if (yyyymm == null || "".equals(yyyymm)) {
            throw new IllegalArgumentException("年月がありません。");
        }
        if (yyyymm.length() != 6) {
            throw new IllegalArgumentException("年月はYYYYMM形式です。");
        }
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
            this.yearMonth = YearMonth.parse(yyyymm, dtf);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("不正な年月です。");
        }
    }
}
