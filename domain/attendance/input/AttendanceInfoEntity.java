package com.naosim.dddwork.domain.attendance.input;

public class AttendanceInfoEntity {
    private final WorkDate _workDate;
    private WorkStartTime _workStartTime;
    private WorkEndTime _workEndTime;
    private WorkTimeMinutes _workTime;
    private OverWorkTimeMinutes _overWorkTime;

    public AttendanceInfoEntity(WorkDate workDate, WorkStartTime workStartTime, WorkEndTime workEndTime) {
        _workDate = workDate;
        _workStartTime = workStartTime;
        _workEndTime = workEndTime;
        calculateWorkTimeAndOverWorkTime();
    }

    public AttendanceInfoEntity updateWorkStartTimeAndWorkEndTime(
            WorkStartTime workStartTime,
            WorkEndTime workEndTime) {
        _workStartTime = workStartTime;
        _workEndTime = workEndTime;
        calculateWorkTimeAndOverWorkTime();

        return this;
    }

    private void calculateWorkTimeAndOverWorkTime() {
        _workTime = new WorkTimeMinutes(getWorkStartTime(), getWorkEndTime());
        _overWorkTime = new OverWorkTimeMinutes(getWorkTime());
    }

    public WorkDate getWorkDate() {
        return _workDate;
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
