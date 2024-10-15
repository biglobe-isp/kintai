package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.ScheduledWorkingMinutes;

import java.time.Duration;

/**
 * 1ヶ月の総残業時間
 */
public class MonthlyOverworkingMinutes {
    private Duration monthlyOverworkingMinutes = Duration.ZERO;

    public MonthlyOverworkingMinutes(DailyWorkDataList dataList, MonthlyWorkingMinutes monthlyWorkingMinutes) {

        calcTotal1MonthOverworkTimes(dataList, monthlyWorkingMinutes);
    }

    private void calcTotal1MonthOverworkTimes(DailyWorkDataList dataList, MonthlyWorkingMinutes monthlyWorkingMinutes) {
        ScheduledWorkingMinutes scheduledWorkingMinutes = new ScheduledWorkingMinutes();

        Duration totalScheduledWorkTimes = Duration.ofMinutes(
                scheduledWorkingMinutes.getScheduledWorkTimes().toMinutes()
                        * dataList.getWorkingDays().getValue());

        if(!checkCalcOverworkTimesNecessity(monthlyWorkingMinutes, totalScheduledWorkTimes)) return;

        monthlyOverworkingMinutes = monthlyOverworkingMinutes.plus(
                calcOverworkTimes(monthlyWorkingMinutes, totalScheduledWorkTimes)
        );
    }

    private boolean checkCalcOverworkTimesNecessity(MonthlyWorkingMinutes monthlyWorkingMinutes, Duration totalScheduledWorkTimes) {
        return monthlyWorkingMinutes.getValue().toMinutes() >= totalScheduledWorkTimes.toMinutes();
    }

    private Duration calcOverworkTimes(MonthlyWorkingMinutes monthlyWorkingMinutes, Duration totalScheduledWorkTimes) {
        return monthlyWorkingMinutes.getValue().minus(totalScheduledWorkTimes);
    }

    public Duration getValue() {
        return monthlyOverworkingMinutes;
    }
}