package com.naosim.dddwork.domain;

import java.util.List;

public final class TotalTime {
    public int calculateTotalWorkMonthTime(
            List<StoreWorkTime> storeWorkTimeList) {
        int totalWorkTime = 0;
        for (StoreWorkTime storeWorkTime : storeWorkTimeList) {
            totalWorkTime += storeWorkTime.getWorkMinutes();
        }
        return totalWorkTime;
    }

    public int calculateTotalOverWorkMonthTime(
            List<StoreWorkTime> storeWorkTimeList) {
        int totalOverWorkTime = 0;
        for (StoreWorkTime storeWorkTime : storeWorkTimeList) {
            totalOverWorkTime += storeWorkTime.getOvertimeMinutes();
        }
        return totalOverWorkTime;
    }
}
