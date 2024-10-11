package com.naosim.dddwork.domain.daily_work;

import java.time.Duration;

/**
 * 1日の実働時間
 */
public class Total1DayWorkTimes {
    private final Duration total1DayWorkTimes;

    public Total1DayWorkTimes(StartWorkTime startWorkTime, EndWorkTime endWorkTime) {
        total1DayWorkTimes = CalcTotal1DayWorkTimes(startWorkTime, endWorkTime);
    }

    private Duration CalcTotal1DayWorkTimes(StartWorkTime startWorkTime, EndWorkTime endWorkTime) {
        return CalculateWorkTimesDomainService.calcWorkTimes(startWorkTime, endWorkTime);
    }

    public Duration getValue() {
        return total1DayWorkTimes;
    }
}