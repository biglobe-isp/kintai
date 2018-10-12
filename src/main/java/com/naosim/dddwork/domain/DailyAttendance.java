package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class DailyAttendance {
    @Getter
    private final WorkDay workDay;
    @Getter
    private final StartingHours startingHours;
    @Getter
    private final ClosingHours closingHours;
    @Getter
    private final WorkingHours workingHours;
    @Getter
    private final OvertimeHours overtimeHours;
}
