package com.naosim.dddwork.domain;

public class StoreWorkTime {
    private final WorkMinutes workMinutes;
    private final WorkMinutes overTimeMinutes;

    public StoreWorkTime(WorkMinutes workMinutes, WorkMinutes overTimeMinutes) {
        this.workMinutes = workMinutes;
        this.overTimeMinutes = overTimeMinutes;
    }

    public WorkMinutes getWorkMinutes() {
        return workMinutes;
    }

    public WorkMinutes getOvertimeMinutes() {
        return overTimeMinutes;
    }
}
