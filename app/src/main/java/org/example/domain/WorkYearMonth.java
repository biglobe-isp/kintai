package org.example.domain;

import lombok.Value;

import java.time.YearMonth;

import static org.example.utils.DateTimeFormatters.YEAR_MONTH;

@Value
public class WorkYearMonth {
    YearMonth value;
    public static WorkYearMonth of(String value) {
        try {
            YearMonth yearMonth = YearMonth.parse(value, YEAR_MONTH);
            return new WorkYearMonth(yearMonth);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid work year-month format. Expected format: yyyyMM (e.g., 202308).");
        }
    }
}
