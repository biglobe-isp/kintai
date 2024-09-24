package com.naosim.dddwork.domain;

import java.time.Duration;

/**
 * 総勤務時間
 */
class Total1MonthNormalWorkTimes {
    private final Total1MonthTimeMoments totalNormalWorkTimes = new Total1MonthTimeMoments();

    Total1MonthNormalWorkTimes(Total1MonthWorkTimes workTimes, Total1MonthOverworkTimes overworkTimes) {
        calcTotalNormalWorkTimes(workTimes, overworkTimes);
    }

    private void calcTotalNormalWorkTimes(Total1MonthWorkTimes workTimes, Total1MonthOverworkTimes overworkTimes) {
        Duration adjustedNormalWorkTimes = workTimes.getTotal1MonthWorkTimes().GetTotalTimes().minus(overworkTimes.getTotalOverworkTimes().GetTotalTimes());
        totalNormalWorkTimes.AddTimes(adjustedNormalWorkTimes);
    }
}