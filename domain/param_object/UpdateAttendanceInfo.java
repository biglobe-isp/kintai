package com.naosim.dddwork.domain.param_object;

import com.naosim.dddwork.domain.value_object.Time;

public class UpdateAttendanceInfo {
    private final Time startTime;
    private final Time endTime;
    public UpdateAttendanceInfo(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Time getStartTime() {return startTime;}
    public Time getEndTime() {return endTime;}
}
