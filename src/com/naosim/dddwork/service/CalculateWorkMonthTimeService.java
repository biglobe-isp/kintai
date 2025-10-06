package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.StoreMonthWorkData;
import com.naosim.dddwork.domain.StoreWorkTime;
import com.naosim.dddwork.domain.TotalTime;
import com.naosim.dddwork.domain.WorkDataRepository;

import java.time.YearMonth;
import java.util.List;

public class CalculateWorkMonthTimeService {
    private final WorkDataRepository workDataRepository;
    TotalTime totalTime = new TotalTime();

    public CalculateWorkMonthTimeService(WorkDataRepository workDataRepository) {
        this.workDataRepository = workDataRepository;
    }

    public StoreMonthWorkData totalMonthWorkTime(YearMonth yearMonth) {
        List<StoreWorkTime> storeWorkTimeList = workDataRepository.findMonthWorkTime(yearMonth);

        int totalMonthWorkMinutes = totalTime.calculateTotalWorkMonthTime(
                storeWorkTimeList
        );
        int totalMonthOverWorkMinutes = totalTime.calculateTotalOverWorkMonthTime(
                storeWorkTimeList
        );
        return new StoreMonthWorkData(totalMonthWorkMinutes, totalMonthOverWorkMinutes);
    }
}
// 返り値が二つ...
