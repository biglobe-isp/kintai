package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.DailyWorkData;

import java.time.Duration;

/**
 * 1ヶ月の総実働時間
 */
public class MonthlyWorkingMinutes {
    Duration total1MonthWorkTimes = Duration.ZERO;

    public MonthlyWorkingMinutes(DailyWorkDataList dailyWorkDataList) {
        calcTotal1MonthWorkTimes(dailyWorkDataList);
    }

    public void calcTotal1MonthWorkTimes(DailyWorkDataList dailyWorkDataList) {
        for (DailyWorkData data : dailyWorkDataList.getDailyWorkDataList()) {
            total1MonthWorkTimes = total1MonthWorkTimes.plus(data.getTotalWorkTimes().getValue());
        }
    }

    public Duration getValue() {
        return total1MonthWorkTimes;
    }
}