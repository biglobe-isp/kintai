package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.service.CalculateWorkMonthTimeService;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class CalculateTotalWorkTimeController {
    CalculateWorkMonthTimeService calculateWorkMonthTimeService;

    public CalculateTotalWorkTimeController(WorkDataRepository workDataRepository) {
        this.calculateWorkMonthTimeService = new CalculateWorkMonthTimeService(workDataRepository);
    }

    public void callTotalMonthTime(String yearMonthString) {
        // コントローラー層で年月ごとに分岐？
        YearMonth yearMonth = YearMonth.parse(yearMonthString, DateTimeFormatter.ofPattern("uuuuMM"));
        //　値をサービス層のメソッドに渡す
        calculateWorkMonthTimeService.totalMonthWorkTime(yearMonth);
    }
}
