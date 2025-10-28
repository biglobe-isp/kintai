package org.example.domain;

import lombok.Value;

@Value
public class WorkTime {
    WorkStartTime workStartTime;
    WorkEndTime workEndTime;

    public WorkTime(WorkStartTime workStartTime, WorkEndTime workEndTime) {
        if (workStartTime.getValue().getHour() * 60 + workStartTime.getValue().getMinute() >
                workEndTime.getValue().getHour() * 60 + workEndTime.getValue().getMinute()) {
            throw new IllegalArgumentException("Work start time must be before work end time");
        }

        this.workStartTime = workStartTime;
        this.workEndTime = workEndTime;
    }
}
