package com.naosim.dddwork.domain;

public class StoreWorkTime {
    private final WorkMinutes workMinutes;
    private final WorkMinutes overtimeMinutes;

    public StoreWorkTime(WorkMinutes workMinutes, WorkMinutes overtimeMinutes) {
        this.workMinutes = workMinutes;
        this.overtimeMinutes = overtimeMinutes;
    }

    public WorkMinutes getWorkMinutes() {
        return workMinutes;
    }

    public WorkMinutes getOvertimeMinutes() {
        return overtimeMinutes;
    }
}
