package com.naosim.dddwork.domain.daily_work;

public class ScheduledWorkTimes {
    private final TimeMoment SCHEDULED_START_TIME = new TimeMoment(9, 0);
    private final TimeMoment SCHEDULED_END_TIME = new TimeMoment(18, 0);
    private final MinutesDifference scheduledWorkTimes = new MinutesDifference(SCHEDULED_START_TIME, SCHEDULED_END_TIME);

    public ScheduledWorkTimes() {
    }

    public MinutesDifference getScheduledWorkTimes() {
        return new MinutesDifference(scheduledWorkTimes);
    }

    TimeMoment getScheduledStartTime() {
        return new TimeMoment(SCHEDULED_START_TIME);
    }
}