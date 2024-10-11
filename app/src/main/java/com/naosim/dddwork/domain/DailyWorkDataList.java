package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.DailyWorkData;

import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * 勤務記録の一覧
 */
public class DailyWorkDataList {
    private final List<DailyWorkData> workDataList = new ArrayList<>();
    private int workingDays;

    DailyWorkDataList() {
        updateWorkingDays();
    }

    void updateWorkingDays() {
        workingDays = workDataList.size();
        if(validateWorkingDays()) System.err.println("勤務記録の日数が不正です。データ重複の恐れあり。");
    }

    boolean validateWorkingDays() {
        if (workDataList.isEmpty()) return false;

        // 月末日=対象月の日数を超えていないかバリデーション
        return workingDays > workDataList.get(0).getWorkDate().getValue()
                .with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
    }

    List<DailyWorkData> getDailyWorkDataList() {
        List<DailyWorkData> returnList = new ArrayList<>();

        for (DailyWorkData data : workDataList){
            returnList.add(new DailyWorkData(data));
        }

        return returnList;
    }

    int getWorkingDays() {
        return workingDays;
    }
}