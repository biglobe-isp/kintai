package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.ScheduledWorkTimes;

class Total1MonthOverworkTimes {
    private final Total1MonthTimeMoments totalOverworkTimes;

    Total1MonthOverworkTimes(DailyWorkDataList dataList, Total1MonthWorkTimes total1MonthWorkTimes) {
        this.totalOverworkTimes = new Total1MonthTimeMoments();

        calcTotal1MonthOverworkTimes(dataList, total1MonthWorkTimes);
    }

    private void calcTotal1MonthOverworkTimes(DailyWorkDataList dataList, Total1MonthWorkTimes total1MonthWorkTimes) {
        ScheduledWorkTimes scheduledWorkTimes = new ScheduledWorkTimes();
        long totalScheduledWorkTimes = scheduledWorkTimes.getScheduledWorkTimes().getMinutesDifference() * dataList.getWorkingDays();

        if(!checkCalcOverworkTimesNecessity(total1MonthWorkTimes, totalScheduledWorkTimes)) return;

        totalOverworkTimes.AddTimes(calcOverworkTimes(total1MonthWorkTimes, totalScheduledWorkTimes));
    }

    private boolean checkCalcOverworkTimesNecessity(Total1MonthWorkTimes total1MonthWorkTimes, long totalScheduledWorkTimes) {
        return total1MonthWorkTimes.getTotal1MonthWorkTimes().GetTotalTimes() >= totalScheduledWorkTimes;
    }

    private long calcOverworkTimes(Total1MonthWorkTimes total1MonthWorkTimes, long totalScheduledWorkTimes) {
        return total1MonthWorkTimes.getTotal1MonthWorkTimes().GetTotalTimes() - totalScheduledWorkTimes;
    }

    Total1MonthTimeMoments getTotalOverworkTimes() {
        return new Total1MonthTimeMoments(totalOverworkTimes);
    }
}