package com.naosim.dddwork.domain.daily_work;

import java.time.LocalTime;

/**
 * 勤務終了時刻
 */
public class EndWorkTime {
    private final LocalTime endWorkTime;

    public EndWorkTime(LocalTime endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    public LocalTime getValue() {return endWorkTime; }
}