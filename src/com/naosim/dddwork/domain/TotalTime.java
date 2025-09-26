package com.naosim.dddwork.domain;

import java.util.List;

public final class TotalTime {
    public WorkMinutes calculateTotalWorkMonthTime(
            List<StoreWorkTime> storeWorkTimeList) {
        int totalWorkTime = 0;
        for (StoreWorkTime storeWorkTime : storeWorkTimeList) {
            totalWorkTime += storeWorkTime.getWorkMinutes().getWorkMinutes();
        }
        return new WorkMinutes(totalWorkTime);
    }

    public WorkMinutes calculateTotalOverWorkMonthTime(
            List<StoreWorkTime> storeWorkTimeList) {
        int totalOverWorkTime = 0;
        for (StoreWorkTime storeWorkTime : storeWorkTimeList) {
            totalOverWorkTime += storeWorkTime.getOvertimeMinutes().getWorkMinutes();
        }
        return new WorkMinutes(totalOverWorkTime);
    }
}
