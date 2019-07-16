package refactor.domain;

import lombok.Getter;
import lombok.NonNull;

public class DailyAttendanceRecord {
    @Getter
    @NonNull
    private final WorkingDay workingDay;
    @Getter
    @NonNull
    private final StartTime startTime;
    @Getter
    @NonNull
    private final EndTime endTime;
    @Getter
    @NonNull
    private final AttendanceInputTime attendanceInputTime;
    @Getter
    @NonNull
    private final WorkingHours workingHours;
    @Getter
    @NonNull
    private final OvertimeHours overtimeHours;

    public DailyAttendanceRecord(
            WorkingDay workingDay,
            StartTime startTime,
            EndTime endTime,
            AttendanceInputTime attendanceInputTime) {
        this.workingDay = workingDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attendanceInputTime = attendanceInputTime;
        this.workingHours = new WorkingHours(startTime, endTime, new BreakTime(endTime));
        this.overtimeHours = new OvertimeHours(workingHours);
    }
}
