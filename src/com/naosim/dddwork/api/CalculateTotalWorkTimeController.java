package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.service.CalculateWorkMonthTimeService;

public class CalculateTotalWorkTimeController {
    CalculateWorkMonthTimeService calculateWorkMonthTimeService;

    public CalculateTotalWorkTimeController(WorkDataRepository workDataRepository) {
        this.calculateWorkMonthTimeService = new CalculateWorkMonthTimeService(workDataRepository);
    }

    public void callTotalMonthTime(String yearMonth) {
        // コントローラー層で年月ごとに分岐？
    }
}
