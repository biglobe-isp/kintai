package jp.co.biglobe.lib.publication.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("ALL")
public class LocalDateTimeFormatter {

    public static String format_yyyyMMddHH(final LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DateFormatPattern.yyyyMMddHH.getValue()));
    }

    public static String format_yyyyMMddHHmm(final LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DateFormatPattern.yyyyMMddHHmm.getValue()));
    }

    public static String format_yyyyMMdd(final LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DateFormatPattern.yyyyMMdd.getValue()));
    }


}