package com.naosim.dddwork.domain.daily_work;

import java.time.Duration;

/**
 * 勤務記録データ
 */
public class DailyWorkData {
    private final WorkDate workDate;
    private final DailyWorkingHours totalWorkTimes;

    public DailyWorkData(WorkDate workDate, StartWorkTime startTime, EndWorkTime endTime) {
        this.workDate = new WorkDate(workDate.getValue());
        totalWorkTimes = new DailyWorkingHours(startTime, endTime);
    }

    public DailyWorkData(DailyWorkData data) {
        this.workDate = new WorkDate(data.workDate.getValue());
        this.totalWorkTimes = data.totalWorkTimes;
    }

    public DailyWorkData(WorkDate workDate, Duration workingMinutes) {
        this.workDate = workDate;
        this.totalWorkTimes = new DailyWorkingHours(workingMinutes);
    }

    public WorkDate getWorkDate() {
        return new WorkDate(workDate.getValue());
    }

    public DailyWorkingHours getTotalWorkTimes() {
        return totalWorkTimes;
    }
}