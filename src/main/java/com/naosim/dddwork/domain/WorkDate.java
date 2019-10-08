package com.naosim.dddwork.domain;

/***
 * 勤務日を表す値オブジェクト
 */
public class WorkDate {
    private String date;

    public WorkDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.date;
    }
}
