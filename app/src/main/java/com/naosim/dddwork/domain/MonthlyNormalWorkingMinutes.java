package com.naosim.dddwork.domain;

import java.time.Duration;

/**
 * 1ヶ月の総勤務時間
 */
public class MonthlyNormalWorkingMinutes {
    private Duration monthlyNormalWorkingMinutes = Duration.ZERO;

    public MonthlyNormalWorkingMinutes(MonthlyWorkingMinutes workTimes, MonthlyOverworkingMinutes overworkTimes) {
        calcTotalNormalWorkTimes(workTimes, overworkTimes);
    }

    private void calcTotalNormalWorkTimes(MonthlyWorkingMinutes workTimes, MonthlyOverworkingMinutes overworkTimes) {
        Duration adjustedNormalWorkTimes = workTimes.getValue().minus(overworkTimes.getValue());
        monthlyNormalWorkingMinutes = monthlyNormalWorkingMinutes.plus(adjustedNormalWorkTimes);
    }

    public Duration getValue() {
        return monthlyNormalWorkingMinutes;
    }
}