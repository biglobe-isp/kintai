package com.naosim.dddwork.domain.service;

import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;

public interface MonthlySummaryCalculable {
    MonthlySummary aggregateSpecifiedMonthAttendance(YearMonth yearMonth);
}
