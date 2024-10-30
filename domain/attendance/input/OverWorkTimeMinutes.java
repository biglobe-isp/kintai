package com.naosim.dddwork.domain.attendance.input;

public class OverWorkTimeMinutes {
    private static final Integer MAX_DAILY_WORK_MINUTES = 480;
    private final Integer value;

    public OverWorkTimeMinutes(WorkTimeMinutes workTimeMinutes) {
        this.value = Math.max(0, workTimeMinutes.getValue() - MAX_DAILY_WORK_MINUTES);
    }

    public int getValue() {
        return value;
    }
}
