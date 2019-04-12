package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.WorkTimeOfMonth;
import com.naosim.dddwork.domain.YearMonth;
import org.springframework.stereotype.Component;

@Component
class AttendanceSummaryFactory {

    AttendanceSummary create(YearMonth yearMonth, WorkTimeOfMonth workTimeOfMonth) {
        return new AttendanceSummary(
                yearMonth,
                workTimeOfMonth.getTotalWorkMinute(),
                workTimeOfMonth.getTotalOverWorkMinute()
        );
    }
}
