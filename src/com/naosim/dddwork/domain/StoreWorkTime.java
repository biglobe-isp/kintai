package com.naosim.dddwork.domain;

public class StoreWorkTime {
    private final int workMinutes;
    private final int overTimeMinutes;

    public StoreWorkTime(int workMinutes, int overTimeMinutes) {
        this.workMinutes = workMinutes;
        this.overTimeMinutes = overTimeMinutes;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

    public int getOvertimeMinutes() {
        return overTimeMinutes;
    }
}
