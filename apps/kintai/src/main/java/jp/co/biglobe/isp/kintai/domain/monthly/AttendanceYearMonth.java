package jp.co.biglobe.isp.kintai.domain.monthly;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public record AttendanceYearMonth(YearMonth value) {
    public String format(DateTimeFormatter formatter) {
        return formatter.format(value);
    }
}
