package com.naosim.dddwork.domain;

import lombok.Value;

@Value
public class AttendanceSummary implements WorkTimeOfMonth {

    private final YearMonth yearMonth;
    private final WorkMinute totalWorkMinute;
    private final WorkMinute totalOverWorkMinute;
}
