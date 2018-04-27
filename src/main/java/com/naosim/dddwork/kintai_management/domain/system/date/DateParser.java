package com.naosim.dddwork.kintai_management.domain.system.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public final class DateParser {

    public static LocalDate parse_yyyyMMdd(final String value) {
        return LocalDate.parse(value, createFormatter(DateDefaultPattern.yyyyMMdd));
    }

    private static DateTimeFormatter createFormatter(final DateDefaultPattern dateDefaultPattern) {
        return DateTimeFormatter.ofPattern(dateDefaultPattern.getValue()).withResolverStyle(ResolverStyle.STRICT);
    }

    private DateParser() {
    }
}
