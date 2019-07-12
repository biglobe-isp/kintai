package refactor.domain;

import java.util.Objects;

public class DailyAttendanceRecord {
    private final WorkingDay workingDay;
    private final StartTime startTime;
    private final EndTime endTime;
    private final AttendanceInputTime attendanceInputTime;
    private final WorkingHours workingHours;
    private final OvertimeHours overtimeHours;

    public DailyAttendanceRecord(
            WorkingDay workingDay,
            StartTime startTime,
            EndTime endTime,
            AttendanceInputTime attendanceInputTime) {
        this.workingDay = Objects.requireNonNull(workingDay);
        this.startTime = Objects.requireNonNull(startTime);
        this.endTime = Objects.requireNonNull(endTime);
        this.attendanceInputTime = Objects.requireNonNull(attendanceInputTime);
        this.workingHours = new WorkingHours(startTime, endTime, new BreakTime(endTime));
        this.overtimeHours = new OvertimeHours(workingHours);
    }

    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public EndTime getEndTime() {
        return endTime;
    }

    public AttendanceInputTime getAttendanceInputTime() {
        return attendanceInputTime;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public OvertimeHours getOvertimeHours() {
        return overtimeHours;
    }
}
