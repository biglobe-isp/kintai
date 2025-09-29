package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.StoreMonthWorkData;
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
        // コントローラー層で年月ごとに分岐
        YearMonth yearMonth = YearMonth.parse(yearMonthString, DateTimeFormatter.ofPattern("uuuuMM"));
        StoreMonthWorkData monthWorkData = calculateWorkMonthTimeService.totalMonthWorkTime(yearMonth);
        //　値をサービス層のメソッドに渡す
//        calculateWorkMonthTimeService.totalMonthWorkTime(yearMonth);
        int totalWorkMinutes = monthWorkData
                .getTotalWorkMinutes()
                .getWorkMinutes();
        int totalOverWorkMinutes = monthWorkData
                .getTotalOvertimeMinutes()
                .getWorkMinutes();

        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
    }
}
