package com.naosim.dddwork.domain.daily_work;

/**
 * 勤務実施年月日
 */
public class WorkDate {
    private final DateMoments workDate;

    WorkDate(int year, int month, int day){
        this.workDate = new DateMoments(year, month, day);
    }

    WorkDate(DateMoments workDate) {
        this.workDate = new DateMoments(workDate);
    }

    public DateMoments getDateMoments() {
        return new DateMoments(workDate);
    }
}