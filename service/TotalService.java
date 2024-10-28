package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeDAO;
import com.naosim.dddwork.datasource.WorkTimeEntity;

import java.util.ArrayList;

public class TotalService {
    private final WorkTimeDAO dao = new WorkTimeDAO();

    public TotalData getMonthlyTotal() throws Exception{
        TotalData data = new TotalData();

        ArrayList<WorkTimeEntity> entityList = dao.selectAll();
        data.workTimeMinutesSum = entityList.stream().mapToInt(e -> e.workMinutes).sum();
        data.overWorkTimeMinutesSum = entityList.stream().mapToInt(e -> e.overWorkMinutes).sum();

        return data;
    }
}
