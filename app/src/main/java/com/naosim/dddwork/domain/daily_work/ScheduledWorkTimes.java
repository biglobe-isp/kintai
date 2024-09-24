package com.naosim.dddwork.domain.daily_work;

/**
 * 所定労働時間
 */
public class ScheduledWorkTimes {
    private final TimeMoment SCHEDULED_START_TIME = new TimeMoment(9, 0);
    private final TimeMoment SCHEDULED_END_TIME = new TimeMoment(18, 0);
    private final TimeMomentsDifference scheduledWorkTimes = new TimeMomentsDifference(SCHEDULED_START_TIME, SCHEDULED_END_TIME);

    public ScheduledWorkTimes() {
    }

    public TimeMomentsDifference getScheduledWorkTimes() {
        return new TimeMomentsDifference(scheduledWorkTimes);
    }

    TimeMoment getScheduledStartTime() {
        return new TimeMoment(SCHEDULED_START_TIME);
    }
}