package com.naosim.dddwork.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 勤務日を表す値オブジェクト
 */
public class WorkDate {
    private String date;

    private WorkDate(String date) {
        this.date = date;
    }

    public static WorkDate from(LocalDate lt) {
        return new WorkDate(lt.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
    

    @Override
    public String toString() {
        return this.date;
    }
}
