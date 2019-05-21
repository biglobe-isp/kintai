package com.naosim.dddwork.kintai.domain.model.foundation.date;

import java.time.YearMonth;


/**
 * 勤怠年月
 */
public class AttendanceYearMonth {

    final YearMonth value;


    /* 生成 */

    private AttendanceYearMonth(YearMonth yearMonth) {
        this.value = yearMonth;
    }

    public static AttendanceYearMonth of(String year, String month) {
        return new AttendanceYearMonth(
                YearMonth.of(
                        Integer.valueOf(year),
                        Integer.valueOf(month))
        );
    }

    public static AttendanceYearMonth of(YearMonth yearMonth) {
        return new AttendanceYearMonth(yearMonth);
    }


    /* 比較 */

    public boolean isEqualTo(YearMonth other) {
        return other.equals(value);
    }
}
