package com.naosim.dddwork.domain.time;

import lombok.Getter;


public class WorkingDuration {

    @Getter
    private final EntryTime startTime;
    @Getter
    private final EntryTime endTime;

    public WorkingDuration(EntryTime startTime, EntryTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString()
    {
        return startTime.toString() +"-" + endTime.toString();
    }

}
