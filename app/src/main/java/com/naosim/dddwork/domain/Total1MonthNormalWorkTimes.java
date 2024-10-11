package com.naosim.dddwork.domain;

import java.time.Duration;

/**
 * 総勤務時間
 */
class Total1MonthNormalWorkTimes {
    private Duration totalNormalWorkTimes = Duration.ZERO;

    Total1MonthNormalWorkTimes(Total1MonthWorkTimes workTimes, Total1MonthOverworkTimes overworkTimes) {
        calcTotalNormalWorkTimes(workTimes, overworkTimes);
    }

    private void calcTotalNormalWorkTimes(Total1MonthWorkTimes workTimes, Total1MonthOverworkTimes overworkTimes) {
        Duration adjustedNormalWorkTimes = workTimes.getValue().minus(overworkTimes.getValue());
        totalNormalWorkTimes = totalNormalWorkTimes.plus(adjustedNormalWorkTimes);
    }
}