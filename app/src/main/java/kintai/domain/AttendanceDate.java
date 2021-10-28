package kintai.domain;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 出勤日.
 */
@Value
public class AttendanceDate {
    /**
     * 出勤日
     */
    LocalDate date;

    /**
     * 成形した出勤日文字列を出力する,
     *
     * @return 成形した出勤日文字列
     */
    public String format() {
        return date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
