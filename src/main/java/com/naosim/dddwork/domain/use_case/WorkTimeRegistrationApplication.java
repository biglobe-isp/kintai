package com.naosim.dddwork.domain.use_case;

import com.naosim.dddwork.domain.DailyAttendance;
import com.naosim.dddwork.domain.LaborRegulations;
import com.naosim.dddwork.domain.ClosingHours;
import com.naosim.dddwork.domain.OvertimeHours;
import com.naosim.dddwork.domain.StartingHours;
import com.naosim.dddwork.domain.WorkDay;
import com.naosim.dddwork.domain.WorkingHours;
import com.naosim.dddwork.domain.WorkingTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class WorkTimeRegistrationApplication {
    @Getter
    private  final WorkDay workDay;
    @Getter
    private  final StartingHours startingHours;
    @Getter
    private  final ClosingHours closingHours;
}
