package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.OvertimeHours;
import com.naosim.dddwork.domain.WorkingHours;
import com.naosim.dddwork.domain.WorkingMonth;
import com.naosim.dddwork.domain.use_case.TotalWorkingHoursResult;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class TotalWorkingHoursResultOutput {
    @Getter
    private final WorkingMonth workingMonth;
    @Getter
    private final WorkingHours workingHours;
    @Getter
    private final OvertimeHours overtimeHours;

    TotalWorkingHoursResultOutput(TotalWorkingHoursResult totalWorkingHoursResult) {
        this.workingMonth = totalWorkingHoursResult.getWorkingMonth();
        this.workingHours = totalWorkingHoursResult.getWorkingHours();
        this.overtimeHours = totalWorkingHoursResult.getOvertimeHours();
    }

    void print() {
        System.out.println("集計年月: " + this.getWorkingMonth().getValue());
        System.out.println("勤務時間: " + this.getWorkingHours().getValue() / 60 + "時間" + this.getWorkingHours().getValue() % 60 + "分");
        System.out.println("残業時間: " + this.getOvertimeHours().getValue() / 60 + "時間" + this.getOvertimeHours().getValue() % 60 + "分");
    }
}
