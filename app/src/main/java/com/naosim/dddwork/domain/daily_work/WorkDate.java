package com.naosim.dddwork.domain.daily_work;

import java.time.LocalDate;

/**
 * 勤務実施年月日
 */
public class WorkDate {
    private final LocalDate workDate;

    public WorkDate(LocalDate workDate){
        this.workDate = workDate;
    }

    public LocalDate getValue() {
        return workDate;
    }
}