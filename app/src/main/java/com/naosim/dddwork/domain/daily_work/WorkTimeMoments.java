package com.naosim.dddwork.domain.daily_work;

/**
 * 勤務時刻
 */
public class WorkTimeMoments {
    private final TimeMoment startWorkTime;
    private final TimeMoment endWorkTime;
    private final ScheduledWorkTimes scheduledWorkHours = new ScheduledWorkTimes();

    public WorkTimeMoments(TimeMoment startWorkTime, TimeMoment endWorkTime) {
        this.startWorkTime = new TimeMoment(startWorkTime);
        // if(ValidateStartWorkTime()) System.err.println("勤務開始時間が不正な値です。");

        this.endWorkTime = new TimeMoment(endWorkTime);
        if(validateEndWorkTime()) System.err.println("勤務終了時間が不正な値です。");
    }

    private boolean validateStartWorkTime() {
        return new TimeMomentsDifference(startWorkTime, scheduledWorkHours.getScheduledStartTime()).validateTimeMomentsDifference();
    }

    private boolean validateEndWorkTime() {
        return new TimeMomentsDifference(endWorkTime, endWorkTime).validateTimeMomentsDifference();
    }

    TimeMoment getStartWorkTime() {
        return new TimeMoment(startWorkTime);
    }

    TimeMoment getEndWorkTime() {
        return new TimeMoment(endWorkTime);
    }
}