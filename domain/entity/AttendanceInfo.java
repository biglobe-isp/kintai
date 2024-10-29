package com.naosim.dddwork.domain.entity;

import com.naosim.dddwork.domain.dto.NewAttendanceInfoDTO;
import com.naosim.dddwork.domain.value_object.Date;
import com.naosim.dddwork.domain.value_object.OverWorkTimeMinutes;
import com.naosim.dddwork.domain.value_object.WorkEndTime;
import com.naosim.dddwork.domain.value_object.WorkStartTime;
import com.naosim.dddwork.domain.value_object.WorkTimeMinutes;

public class AttendanceInfo {
    private Date _date;
    private WorkStartTime _workStartTime;
    private WorkEndTime _workEndTime;
    private WorkTimeMinutes _workTime;
    private OverWorkTimeMinutes _overWorkTime;

    public AttendanceInfo(NewAttendanceInfoDTO attendanceInfo) {
        _date = attendanceInfo.getDate();
        _workStartTime = attendanceInfo.getWorkStartTime();
        _workEndTime = attendanceInfo.getWorkEndTime();
        _workTime = new WorkTimeMinutes(getWorkStartTime(), getWorkEndTime());
        _overWorkTime = new OverWorkTimeMinutes(getWorkTime());
    }

    public Date getDate() {
        return _date;
    }

    public WorkStartTime getWorkStartTime() {
        return _workStartTime;
    }

    public WorkEndTime getWorkEndTime() {
        return _workEndTime;
    }

    public WorkTimeMinutes getWorkTime() {
        return _workTime;
    }

    public OverWorkTimeMinutes getOverWorkTime() {
        return _overWorkTime;
    }
}
