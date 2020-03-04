package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import com.naosim.dddwork.service.MonthlySummaryService;
import org.springframework.stereotype.Controller;

@Controller
public class MonthlyTotalController {
    private final MonthlySummaryService monthlySummaryService;

    public MonthlyTotalController(MonthlySummaryService monthlySummaryService) {
        this.monthlySummaryService = monthlySummaryService;
    }

    public void monthlyTotal(YearMonth yearMonth) {
        MonthlySummary monthlySummary = monthlySummaryService.acquireMonthlyTotal(yearMonth);

        System.out.println("勤務時間: " + monthlySummary.getWorkingHours().getMonthlyTotalString());
        System.out.println("残業時間: " + monthlySummary.getOverTimeHours().getMonthlyTotalString());
    }
}
