package com.naosim.dddwork.domain;

public class StoreMonthWorkData {
    private final int totalWorkMinutes;
    private final int totalOvertimeMinutes;

    public StoreMonthWorkData(int totalWorkMinutes, int totalOvertimeMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
        this.totalOvertimeMinutes = totalOvertimeMinutes;
    }

    // 値を取得するためのゲッター
    public int getTotalWorkMinutes() {
        return totalWorkMinutes;
    }

    public int getTotalOvertimeMinutes() {
        return totalOvertimeMinutes;
    }
}
