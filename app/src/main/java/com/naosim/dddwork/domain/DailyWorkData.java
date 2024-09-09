package com.naosim.dddwork.domain;

class DailyWorkData {
    private final WorkDate workDate;
    private final Total1DayWorkTimes totalWorkTimes;

    DailyWorkData(WorkDate workDate, TimeMoment startTime, TimeMoment endTime) {
        this.workDate = new WorkDate(workDate);
        totalWorkTimes = new Total1DayWorkTimes(new WorkTimeMoments(startTime, endTime));
    }

    WorkDate getWorkDate() {
        return new WorkDate(workDate);
    }
}