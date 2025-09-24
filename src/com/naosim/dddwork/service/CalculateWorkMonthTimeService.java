package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.Rest;
import com.naosim.dddwork.domain.TotalTime;
import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.WorkMinutes;
import com.naosim.dddwork.domain.WorkTime;

import java.time.YearMonth;
import java.util.List;

public class CalculateWorkMonthTimeService {
    private final WorkDataRepository workDataRepository;

    public CalculateWorkMonthTimeService(WorkDataRepository workDataRepository) {
        this.workDataRepository = workDataRepository;
    }

    public WorkMinutes totalMonthWorkTime(YearMonth yearMonth, TotalTime totalTime) {
        List<WorkTime> workTimeList = workDataRepository.findMonthWorkTime(yearMonth);

        WorkMinutes totalMonthWorkMinutes = totalTime.calculateTotalWorkMonthTime(
                workTimeList,
                Rest.getLunchBreak(),
                Rest.getEveningBreak(),
                Rest.getNightBreak()
        );
        WorkMinutes totalMonthOverWorkMinutes = totalTime.calculateTotalOverWorkMonthTime(
                workTimeList,
                Rest.getLunchBreak(),
                Rest.getEveningBreak(),
                Rest.getNightBreak()
        );
    }
}
// 返り値が二つ...
