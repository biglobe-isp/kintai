package com.naosim.dddwork.domain;

public final class WorkMinutes {
    private final int workMinutes;

    public WorkMinutes(int workMinutes) {
        if (workMinutes < 0)
            throw new IllegalArgumentException("workMinutes < 0");
        this.workMinutes = workMinutes;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }
}
