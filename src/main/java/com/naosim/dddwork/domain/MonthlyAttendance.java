package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.use_case.TotalWorkingHoursResult;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class MonthlyAttendance {
    @Getter
    private final WorkingMonth workingMonth;
    @Getter
    private final DailyAttendanceList dailyAttendanceList;

    public TotalWorkingHoursResult makeTotalWorkingHoursResult() {
        return new TotalWorkingHoursResult(
                new WorkingMonth(this.getWorkingMonth().getValue()),
                new WorkingHours(this.getDailyAttendanceList().getValue().stream().mapToInt(dailyAttendance -> dailyAttendance.getWorkingHours().getValue()).sum()),
                new OvertimeHours(this.getDailyAttendanceList().getValue().stream().mapToInt(dailyAttendance -> dailyAttendance.getOvertimeHours().getValue()).sum())
        );
    }
}
