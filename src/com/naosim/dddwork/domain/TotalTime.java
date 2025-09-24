package com.naosim.dddwork.domain;

import java.util.List;

public final class TotalTime {
    public WorkMinutes calculateTotalWorkMonthTime(
            List<WorkTime> workTimes,
            Hour lunchBreak,
            Hour eveningBreak,
            Hour nightBreak) {
        int totalWorkTime = 0;
        for (WorkTime workTime : workTimes) {
            totalWorkTime += workTime.calculateWorkTimeMinutes(lunchBreak, eveningBreak, nightBreak).getWorkMinutes();
        }
        return new WorkMinutes(totalWorkTime);
    }

    public WorkMinutes calculateTotalOverWorkMonthTime(
            List<WorkTime> workOverTimes,
            Hour lunchBreak,
            Hour eveningBreak,
            Hour nightBreak) {
        int totalOverWorkTime = 0;
        for (WorkTime workOverTime : workOverTimes) {
            totalOverWorkTime += workOverTime.calculateOverWorkTimeMinutes(lunchBreak, eveningBreak, nightBreak)
                    .getWorkMinutes();
        }
        return new WorkMinutes(totalOverWorkTime);
    }
}
