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
        return new WorkingHours(startTime, endTime).calculateActualWorkingHours();
    }

    public OvertimeHours getOvertimeHours() {
        return new WorkingHours(startTime, endTime).calculateOvertimeHours();
    }
}
