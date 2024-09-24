package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.ScheduledWorkTimes;

import java.time.Duration;

/**
 * 総残業時間
 */
class Total1MonthOverworkTimes {
    private final Total1MonthTimeMoments totalOverworkTimes;


    Total1MonthOverworkTimes(DailyWorkDataList dataList, Total1MonthWorkTimes total1MonthWorkTimes) {
        this.totalOverworkTimes = new Total1MonthTimeMoments();

        calcTotal1MonthOverworkTimes(dataList, total1MonthWorkTimes);
    }

    private void calcTotal1MonthOverworkTimes(DailyWorkDataList dataList, Total1MonthWorkTimes total1MonthWorkTimes) {
        ScheduledWorkTimes scheduledWorkTimes = new ScheduledWorkTimes();
        // ↓これインスタンスに移した方が良いかも？
        Duration totalScheduledWorkTimes = Duration.ZERO;
        totalScheduledWorkTimes = totalScheduledWorkTimes.plusMinutes(scheduledWorkTimes.getScheduledWorkTimes().getTimeMomentsDifference().toMinutes() * dataList.getWorkingDays());

        if(!checkCalcOverworkTimesNecessity(total1MonthWorkTimes, totalScheduledWorkTimes)) return;

        totalOverworkTimes.AddTimes(calcOverworkTimes(total1MonthWorkTimes, totalScheduledWorkTimes));
    }

    private boolean checkCalcOverworkTimesNecessity(Total1MonthWorkTimes total1MonthWorkTimes, Duration totalScheduledWorkTimes) {
        return total1MonthWorkTimes.getTotal1MonthWorkTimes().GetTotalTimes().toMinutes() >= totalScheduledWorkTimes.toMinutes();
    }

    private Duration calcOverworkTimes(Total1MonthWorkTimes total1MonthWorkTimes, Duration totalScheduledWorkTimes) {
        return total1MonthWorkTimes.getTotal1MonthWorkTimes().GetTotalTimes().minus(totalScheduledWorkTimes);
    }

    Total1MonthTimeMoments getTotalOverworkTimes() {
        return new Total1MonthTimeMoments(totalOverworkTimes);
    }
}