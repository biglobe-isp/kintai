package com.naosim.dddwork.domain;

public final class WorkTimeMinutes {
    private final int value;
    public WorksTimeMinutes(int minutes) {
        if (minutes < 0)
            throw new IllegalArgumentException("minutes < 0");
        this.value = minutes;
    }
}
