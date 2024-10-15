package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.DailyWorkData;

import java.util.ArrayList;
import java.util.List;

/**
 * 勤務記録の一覧
 */
public class DailyWorkDataList {
    private final List<DailyWorkData> workDataList = new ArrayList<>();
    private WorkingDays workingDays;

    public void addDailyWorkData(DailyWorkData data) {
        workDataList.add(data);

        updateWorkingDays();
    }

    void updateWorkingDays() {
        workingDays = new WorkingDays(this);
    }

    List<DailyWorkData> getDailyWorkDataList() {
        List<DailyWorkData> returnList = new ArrayList<>();

        for (DailyWorkData data : workDataList){
            returnList.add(new DailyWorkData(data));
        }

        return returnList;
    }

    public WorkingDays getWorkingDays() {
        return workingDays;
    }
}