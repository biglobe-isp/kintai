package com.naosim.dddwork.kintai.domain.model.foundation.date;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * 勤怠日付
 */
@Getter
//TODO: Getterは暫定的に使用中
public class AttendanceDate {

    final LocalDate localDate;

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

    public AttendanceDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    public String storedValue() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return localDate.format(formatter);
    }
}
