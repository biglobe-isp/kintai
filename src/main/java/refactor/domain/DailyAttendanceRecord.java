package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class DailyAttendanceRecord {
    @NonNull
    private final WorkingDay workingDay;
    @NonNull
    private final StartTime startTime;
    @NonNull
    private final EndTime endTime;
    @NonNull
    private final AttendanceInputTime attendanceInputTime;

    public ActualWorkingHours getActualWorkingHours() {
        return new WorkingHours(startTime, endTime).exclude(LaborRegulations.getBreakTimeList());
    }

    public OvertimeHours getOvertimeHours() {
        int actualWorkingHours = getActualWorkingHours().getMinutes();
        int overtimeHours = Math.max(actualWorkingHours - LaborRegulations.getDailyWorkingTime().getMinutes(), 0);
        return new OvertimeHours(overtimeHours);
    }
}
