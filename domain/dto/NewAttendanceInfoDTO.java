package com.naosim.dddwork.domain.dto;

import com.naosim.dddwork.domain.value_object.Date;
import com.naosim.dddwork.domain.value_object.WorkEndTime;
import com.naosim.dddwork.domain.value_object.WorkStartTime;

public class NewAttendanceInfoDTO {
    private final Date date;
    private final WorkStartTime workStartTime;
    private final WorkEndTime workEndTime;

    public NewAttendanceInfoDTO(Date date, WorkStartTime workStartTime, WorkEndTime workEndTime) {
        this.date = date;
        this.workStartTime = workStartTime;
        this.workEndTime = workEndTime;
    }

    public Date getDate() {
        return date;
    }

    public WorkStartTime getWorkStartTime() {
        return workStartTime;
    }

    public WorkEndTime getWorkEndTime() {
        return workEndTime;
    }
}
