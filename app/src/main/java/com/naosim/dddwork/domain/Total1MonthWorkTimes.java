package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.DailyWorkData;

/**
 * 総実働時間
 */
class Total1MonthWorkTimes {
    private final Total1MonthTimeMoments total1MonthWorkTimes;

    Total1MonthWorkTimes(DailyWorkDataList dailyWorkDataList) {
        total1MonthWorkTimes = new Total1MonthTimeMoments();

        calcTotal1MonthWorkTimes(dailyWorkDataList);
    }

    private void calcTotal1MonthWorkTimes(DailyWorkDataList dailyWorkDataList) {
        for (DailyWorkData data : dailyWorkDataList.getDailyWorkDataList()) {
            total1MonthWorkTimes.AddTimes(data.getTotalWorkTimes().getTotal1DayWorkTimes().getTimeMomentsDifference());
        }
    }

    Total1MonthTimeMoments getTotal1MonthWorkTimes() {
        return new Total1MonthTimeMoments(total1MonthWorkTimes);
    }
}