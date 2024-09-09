package com.naosim.dddwork.domain;

import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

class DailyWorkDataList {
    private final List<DailyWorkData> workDataList = new ArrayList<>();
    private int workingDays;

    DailyWorkDataList() {
        UpdateWorkingDays();
    }

    void InitializeDailyWorkDataList(List<DailyWorkData> fetchWorkDataList) {
        
    }

    void UpdateWorkingDays() {
        workingDays = workDataList.size();
        if(ValidateWorkingDays()) System.err.println("勤務記録の日数が不正です。データ重複の恐れあり。");
    }

    boolean ValidateWorkingDays() {
        if (workDataList.isEmpty()) return false;

        // 月末日=対象月の日数を超えていないかバリデーション
        return workingDays > workDataList.get(0).getWorkDate().getYearMonth()
                .with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
    }
}