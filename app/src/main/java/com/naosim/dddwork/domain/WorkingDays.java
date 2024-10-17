package com.naosim.dddwork.domain;

import java.time.temporal.TemporalAdjusters;

public class WorkingDays {
    private final int workingDays;

    WorkingDays(DailyWorkDataList workDataList) {
        if(validateWorkingDays(workDataList)) throw new IllegalArgumentException("勤務記録の日数が不正です。データ重複の恐れあり。");

        this.workingDays = workDataList.getDailyWorkDataList().size();
    }

    boolean validateWorkingDays(DailyWorkDataList workDataList) {
        if (workDataList.getDailyWorkDataList().isEmpty()) return false;

        // 月末日=対象月の日数を超えていないかバリデーション
        return workingDays > workDataList.getDailyWorkDataList().get(0).getWorkDate().getValue()
                .with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
    }

    public int getValue() {
        return workingDays;
    }
}