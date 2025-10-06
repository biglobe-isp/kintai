package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.MonthlySumWorkTime;
import com.naosim.dddwork.domain.MonthlySumWorkTimeStorage;
import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.WorkTimeStorage;

import java.time.YearMonth;
import java.util.List;

public class CalculateWorkMonthTimeService {
    private final WorkDataRepository workDataRepository;
    MonthlySumWorkTime totalTime = new MonthlySumWorkTime();

    public CalculateWorkMonthTimeService(WorkDataRepository workDataRepository) {
        this.workDataRepository = workDataRepository;
    }

    public MonthlySumWorkTimeStorage totalMonthWorkTime(YearMonth yearMonth) {
        List<WorkTimeStorage> storeWorkTimeList = workDataRepository.findMonthWorkTime(yearMonth);

        int totalMonthWorkMinutes = totalTime.calculateTotalWorkMonthTime(
                storeWorkTimeList
        );
        int totalMonthOverWorkMinutes = totalTime.calculateTotalOverWorkMonthTime(
                storeWorkTimeList
        );
        return new MonthlySumWorkTimeStorage(totalMonthWorkMinutes, totalMonthOverWorkMinutes);
    }
}
// 返り値が二つ...
