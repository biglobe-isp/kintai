package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.WorkMinutes;

public class StoreMonthWorkData {
    private final WorkMinutes totalWorkMinutes;
    private final WorkMinutes totalOvertimeMinutes;

    public StoreMonthWorkData(WorkMinutes totalWorkMinutes, WorkMinutes totalOvertimeMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
        this.totalOvertimeMinutes = totalOvertimeMinutes;
    }

    // 値を取得するためのゲッター
    public WorkMinutes getTotalWorkMinutes() {
        return totalWorkMinutes;
    }

    public WorkMinutes getTotalOvertimeMinutes() {
        return totalOvertimeMinutes;
    }
}
