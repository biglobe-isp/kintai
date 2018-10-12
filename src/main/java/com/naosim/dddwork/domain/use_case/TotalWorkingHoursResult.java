package com.naosim.dddwork.domain.use_case;

import com.naosim.dddwork.domain.OvertimeHours;
import com.naosim.dddwork.domain.WorkingHours;
import com.naosim.dddwork.domain.WorkingMonth;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class TotalWorkingHoursResult {
    @Getter
    private  WorkingMonth workingMonth;
    @Getter
    private  WorkingHours workingHours;
    @Getter
    private  OvertimeHours overtimeHours;
}
