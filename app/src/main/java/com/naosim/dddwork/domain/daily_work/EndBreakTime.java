package com.naosim.dddwork.domain.daily_work;

import java.time.LocalTime;

/**
 * 休憩終了時刻
 */
public class EndBreakTime {
    private LocalTime endBreakTime;

    EndBreakTime(LocalTime endBreakTime) {
        this.endBreakTime = endBreakTime;
    }

    public LocalTime getValue() {
        return endBreakTime;
    }
}