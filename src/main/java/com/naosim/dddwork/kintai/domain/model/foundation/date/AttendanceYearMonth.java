package com.naosim.dddwork.kintai.domain.model.foundation.date;

import lombok.Getter;

import java.time.YearMonth;


/**
 * 勤怠年月
 */
@Getter
//TODO: Getterは暫定的に使用中。
public class AttendanceYearMonth {

    final YearMonth yearMonth;


    /* 生成 */

    private AttendanceYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
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
}
