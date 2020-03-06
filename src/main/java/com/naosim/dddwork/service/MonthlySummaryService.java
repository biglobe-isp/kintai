package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.service.MonthlySummaryCalculable;
import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonthlySummaryService {

    private final MonthlySummaryCalculable monthlySummaryCalculator;

    public MonthlySummary acquireMonthlyTotal(YearMonth yearMonth) {
        return monthlySummaryCalculator.aggregateSpecifiedMonthAttendance(yearMonth);
    }
}
