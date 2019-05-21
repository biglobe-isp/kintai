package com.naosim.dddwork.kintai.domain.model.foundation.date;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


/**
 * 勤怠日付
 */
public class AttendanceDate {

    static DateTimeFormatter STORED_VALUE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

//INSPECT: lombokだとうまくいかなかった...使い方間違っている？
//    @EqualsAndHashCode.Include
    final LocalDate value;


    /* 生成 */

    private AttendanceDate(LocalDate localDate) {
        this.value = localDate;
    }

    public static AttendanceDate of(String storedValue) {

        LocalDate parsed = LocalDate.parse(storedValue, STORED_VALUE_FORMATTER);
        return AttendanceDate.of(parsed);
    }

    public static AttendanceDate of(String year, String month, String dayOfMonth) {

        return AttendanceDate.of(
                LocalDate.of(
                        Integer.valueOf(year),
                        Integer.valueOf(month),
                        Integer.valueOf(dayOfMonth))
        );
    }

    public static AttendanceDate of(LocalDate localDate) {
        return new AttendanceDate(localDate);
    }


    /* 値 */

    public String storedValue() {
        return value.format(STORED_VALUE_FORMATTER);
    }


    /* 比較 */

    public boolean isIncludedIn(AttendanceYearMonth attendanceYearMonth) {

        final YearMonth thisYearMonth = YearMonth.from(value);
        return attendanceYearMonth.isEqualTo(thisYearMonth);
    }


    /* equals&hashCode */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceDate that = (AttendanceDate) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
