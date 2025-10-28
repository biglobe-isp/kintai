package org.example.utils;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatters {
    public static final DateTimeFormatter HOUR_MINUTE = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter HOUR_HYPHEN_MINUTE = DateTimeFormatter.ofPattern("HH_mm");
    public static final DateTimeFormatter YEAR_MONTH = DateTimeFormatter.ofPattern("yyyyMM");
    public static final DateTimeFormatter YEAR_MONTH_DAY = DateTimeFormatter.ofPattern("yyyyMMdd");

}
