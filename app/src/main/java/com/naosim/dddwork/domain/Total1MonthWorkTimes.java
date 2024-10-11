package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.DailyWorkData;

import java.time.Duration;

/**
 * 総実働時間
 */
class Total1MonthWorkTimes {
    Duration total1MonthWorkTimes = Duration.ZERO;

    public void calcTotal1MonthWorkTimes(DailyWorkDataList dailyWorkDataList) {
        for (DailyWorkData data : dailyWorkDataList.getDailyWorkDataList()) {
            total1MonthWorkTimes = total1MonthWorkTimes.plus(data.getTotalWorkTimes().getValue());
        }
    }

    public Duration getValue() {
        return total1MonthWorkTimes;
    }
}