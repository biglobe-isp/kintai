package com.naosim.dddwork.domain.param_object;

import com.naosim.dddwork.domain.value_object.Date;
import com.naosim.dddwork.domain.value_object.Time;

public class NewAttendanceInfo {
    private final Date date;
    private final Time startTime;
    private final Time endTime;
    public NewAttendanceInfo(Date date, Time startTime, Time endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Date getDate() {return date;}
    public Time getStartTime() {return startTime;}
    public Time getEndTime() {return endTime;}
}
