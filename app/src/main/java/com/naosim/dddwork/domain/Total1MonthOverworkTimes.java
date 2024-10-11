package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.ScheduledWorkTimes;

import java.time.Duration;

/**
 * 総残業時間
 */
class Total1MonthOverworkTimes {
    private Duration totalOverworkTimes = Duration.ZERO;

    Total1MonthOverworkTimes(DailyWorkDataList dataList, Total1MonthWorkTimes total1MonthWorkTimes) {

        calcTotal1MonthOverworkTimes(dataList, total1MonthWorkTimes);
    }

    private void calcTotal1MonthOverworkTimes(DailyWorkDataList dataList, Total1MonthWorkTimes total1MonthWorkTimes) {
        ScheduledWorkTimes scheduledWorkTimes = new ScheduledWorkTimes();

        Duration totalScheduledWorkTimes = Duration.ZERO;
        totalScheduledWorkTimes = totalScheduledWorkTimes
                .plusMinutes(scheduledWorkTimes.getScheduledWorkTimes().toMinutes()
                        * dataList.getWorkingDays());

        if(!checkCalcOverworkTimesNecessity(total1MonthWorkTimes, totalScheduledWorkTimes)) return;

        totalOverworkTimes = totalOverworkTimes.plus(
                calcOverworkTimes(total1MonthWorkTimes, totalScheduledWorkTimes)
        );
    }

    private boolean checkCalcOverworkTimesNecessity(Total1MonthWorkTimes total1MonthWorkTimes, Duration totalScheduledWorkTimes) {
        return total1MonthWorkTimes.getValue().toMinutes() >= totalScheduledWorkTimes.toMinutes();
    }

    private Duration calcOverworkTimes(Total1MonthWorkTimes total1MonthWorkTimes, Duration totalScheduledWorkTimes) {
        return total1MonthWorkTimes.getValue().minus(totalScheduledWorkTimes);
    }

    Duration getValue() {
        return totalOverworkTimes;
    }
}