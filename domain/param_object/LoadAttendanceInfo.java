package com.naosim.dddwork.domain.param_object;

import com.naosim.dddwork.domain.value_object.Date;
import com.naosim.dddwork.domain.value_object.Time;
import com.naosim.dddwork.domain.value_object.WorkTimeMinutes;

public class LoadAttendanceInfo {
    private final Date date;
    private final Time startTime;
    private final Time endTime;
    private final WorkTimeMinutes workTime;
    private final WorkTimeMinutes overWorkTime;
    public LoadAttendanceInfo(Date date, Time startTime, Time endTime, WorkTimeMinutes workTime, WorkTimeMinutes overWorkTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workTime = workTime;
        this.overWorkTime = overWorkTime;
    }
    public Date getDate() {return date;}
    public Time getStartTime() {return startTime;}
    public Time getEndTime() {return endTime;}
    public WorkTimeMinutes getWorkTime() {return workTime;}
    public WorkTimeMinutes getOverWorkTime() {return overWorkTime;}
}
