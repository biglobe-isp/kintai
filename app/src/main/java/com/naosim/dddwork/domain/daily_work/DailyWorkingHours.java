package com.naosim.dddwork.domain.daily_work;

import java.time.Duration;

/**
 * 1日の実働時間
 */
public class DailyWorkingHours {
    private final Duration total1DayWorkTimes;

    public DailyWorkingHours(StartWorkTime startWorkTime, EndWorkTime endWorkTime) {
        total1DayWorkTimes = CalcTotal1DayWorkTimes(startWorkTime, endWorkTime);
    }

    public DailyWorkingHours(Duration minutes) {
        total1DayWorkTimes = minutes;
    }

    private Duration CalcTotal1DayWorkTimes(StartWorkTime startWorkTime, EndWorkTime endWorkTime) {
        return CalculateWorkingMinutesDomainService.calcWorkTimes(startWorkTime, endWorkTime);
    }

    public Duration getValue() {
        return total1DayWorkTimes;
    }
}