package com.naosim.dddwork.domain.daily_work;

/**
 * 勤務記録データ
 */
public class DailyWorkData {
    private final WorkDate workDate;
    private final Total1DayWorkTimes totalWorkTimes;

    public DailyWorkData(WorkDate workDate, TimeMoment startTime, TimeMoment endTime) {
        this.workDate = new WorkDate(workDate.getDateMoments());
        totalWorkTimes = new Total1DayWorkTimes(new WorkTimeMoments(startTime, endTime));
    }

    public DailyWorkData(DailyWorkData data) {
        this.workDate = new WorkDate(data.workDate.getDateMoments());
        this.totalWorkTimes = new Total1DayWorkTimes(data.totalWorkTimes);
    }

    public WorkDate getWorkDate() {
        return new WorkDate(workDate.getDateMoments());
    }

    public Total1DayWorkTimes getTotalWorkTimes() {
        return new Total1DayWorkTimes(totalWorkTimes);
    }
}