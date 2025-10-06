package org.example.domain;

import lombok.Value;

import java.time.LocalDate;

import static org.example.utils.DateTimeFormatters.YEAR_MONTH_DAY;

@Value
public class DateToRegister {
    LocalDate value;

    public static DateToRegister of(String value) {
        try {
            LocalDate localDate = LocalDate.parse(value, YEAR_MONTH_DAY);
            return new DateToRegister(localDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date-time format: " + value, e);
        }
    }
}
