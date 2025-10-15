package com.naosim.dddwork.domain;

import java.util.List;

public final class MonthlySumWorkTime {
    public int calculateTotalWorkMonthTime(
            List<WorkTimeStorage> storeWorkTimeList) {
        int totalWorkTime = 0;
        for (WorkTimeStorage storeWorkTime : storeWorkTimeList) {
            totalWorkTime += storeWorkTime.getWorkMinutes();
        }
        return totalWorkTime;
    }

    public int calculateTotalOverWorkMonthTime(
            List<WorkTimeStorage> storeWorkTimeList) {
        int totalOverWorkTime = 0;
        for (WorkTimeStorage storeWorkTime : storeWorkTimeList) {
            totalOverWorkTime += storeWorkTime.getOvertimeMinutes();
        }
        return totalOverWorkTime;
    }
}
