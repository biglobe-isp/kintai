package com.naosim.dddwork.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AttendanceSummary implements WorkTimeOfMonth {

    private final YearMonth yearMonth;
    private final WorkMinute totalWorkMinute;
    private final WorkMinute totalOverWorkMinute;
}
