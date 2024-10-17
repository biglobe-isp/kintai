package com.naosim.dddwork.domain.daily_work;

import java.time.LocalTime;

/**
 * 休憩開始時刻
 */
public class StartBreakTime {
    private final LocalTime startBreakTime;

    StartBreakTime(LocalTime startBreakTime) {
        this.startBreakTime = startBreakTime;
    }

    public LocalTime getValue() {
        return startBreakTime;
    }
}