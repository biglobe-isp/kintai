package com.naosim.dddwork.domain.daily_work;

/**
 * 勤務記録データ
 */
public class DailyWorkData {
    private final WorkDate workDate;
    private final Total1DayWorkTimes totalWorkTimes;

    public DailyWorkData(WorkDate workDate, StartWorkTime startTime, EndWorkTime endTime) {
        this.workDate = new WorkDate(workDate.getValue());
        totalWorkTimes = new Total1DayWorkTimes(startTime, endTime);
    }

    public DailyWorkData(DailyWorkData data) {
        this.workDate = new WorkDate(data.workDate.getValue());
        this.totalWorkTimes = data.totalWorkTimes;
    }

    public WorkDate getWorkDate() {
        return new WorkDate(workDate.getValue());
    }

    public Total1DayWorkTimes getTotalWorkTimes() {
        return totalWorkTimes;
    }
}