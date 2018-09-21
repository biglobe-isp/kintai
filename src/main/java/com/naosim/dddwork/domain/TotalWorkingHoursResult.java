package com.naosim.dddwork.domain;

import lombok.Getter;

public class TotalWorkingHoursResult {
    @Getter
    private WorkingMonth workingMonth;
    @Getter
    private WorkingHours workingHours;
    @Getter
    private OvertimeHours overtimeHours;
}
