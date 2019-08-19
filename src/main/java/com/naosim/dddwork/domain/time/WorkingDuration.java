package com.naosim.dddwork.domain.time;

import lombok.Getter;


public class WorkingDuration {

    @Getter
    private final RecordedTime startTime;
    @Getter
    private final RecordedTime endTime;

    public RecordedTime getStartTime()
    {
        return startTime;
    }
    public RecordedTime getEndTime()
    {
        return endTime;
    }

    public WorkingDuration(RecordedTime startTime, RecordedTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString()
    {
        return startTime.toString() +"-" + endTime.toString();
    }

}
