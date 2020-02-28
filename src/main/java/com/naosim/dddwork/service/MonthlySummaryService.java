package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.IMonthlySummaryCalculator;
import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthlySummaryService {

    private final IMonthlySummaryCalculator monthlySummaryCalculator;

    @Autowired
    public MonthlySummaryService(IMonthlySummaryCalculator monthlySummaryCalculator) {
        this.monthlySummaryCalculator = monthlySummaryCalculator;
    }

    public MonthlySummary acquireMonthlyTotal(YearMonth yearMonth) {
        return monthlySummaryCalculator.aggregateSpecifiedMonthAttendance(yearMonth);
    }
}
