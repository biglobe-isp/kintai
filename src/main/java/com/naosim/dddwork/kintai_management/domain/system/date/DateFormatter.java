package com.naosim.dddwork.kintai_management.domain.system.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

public final class DateFormatter {

    public static String format_yyyyMMdd(final LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(DateDefaultPattern.yyyyMMdd.getValue()));
    }

    private DateFormatter() {
    }
}
