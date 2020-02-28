package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;

public interface IMonthlySummaryCalculator {
    MonthlySummary aggregateSpecifiedMonthAttendance(YearMonth yearMonth);
}
