package com.naosim.dddwork.domain;

import org.springframework.stereotype.Component;

@Component
public class AttendanceSummaryFactory {

    public AttendanceSummary create(YearMonth yearMonth, WorkTimeOfMonth workTimeOfMonth) {
        return new AttendanceSummary(
                yearMonth,
                workTimeOfMonth.getTotalWorkMinute(),
                workTimeOfMonth.getTotalOverWorkMinute()
        );
    }
}
