package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.YearMonth;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
class AttendanceArgumentParser {

    LocalDate parseToLocalDate(String value) {
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    TimePoint parseToTimePoint(String value) {
        if (!Pattern.compile("^\\d{2}[0-5][0-9]$").matcher(value).find()) {
            throw new IllegalArgumentException();
        }
        int hour = Integer.valueOf(value.substring(0, 2));
        int minute = Integer.valueOf(value.substring(2, 4));
        return TimePoint.of(hour, minute);
    }

    YearMonth parseToYearMonth(String value) {
        try {
            LocalDate date = parseToLocalDate(value + "01");
            return YearMonth.of(date.getYear(), date.getMonthValue());
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
