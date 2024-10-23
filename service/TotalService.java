package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeDAO;
import com.naosim.dddwork.datasource.WorkTimeEntity;
import com.naosim.dddwork.domain.WorkTimeSpecification;

import java.util.ArrayList;

public class TotalService {
    private final WorkTimeDAO dao = new WorkTimeDAO();

    public WorkTimeSpecification getMonthlyTotal() throws Exception{
        WorkTimeSpecification spec = new WorkTimeSpecification();

        ArrayList<WorkTimeEntity> entityList = dao.selectAll();
        spec.workMinutes = entityList.stream().mapToInt(e -> e.workMinutes).sum();
        spec.overWorkMinutes = entityList.stream().mapToInt(e -> e.overWorkMinutes).sum();

        return spec;
    }
}
