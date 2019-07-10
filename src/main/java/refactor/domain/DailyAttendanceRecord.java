package refactor.domain;

import java.util.Objects;

public class DailyAttendanceRecord {
    private final Date date;
    private final StartTime startTime;
    private final EndTime endTime;
    private final WorkingHours workingHours;
    private final OvertimeHours overtimeHours;
    private final BreakTime breakTime;
    private final CurrentTime currentTime;

    public DailyAttendanceRecord(
            Date date,
            StartTime startTime,
            EndTime endTime,
            WorkingHours workingHours,
            OvertimeHours overtimeHours,
            BreakTime breakTime,
            CurrentTime currentTime) {
        this.date = Objects.requireNonNull(date);
        this.startTime = Objects.requireNonNull(startTime);
        this.endTime = Objects.requireNonNull(endTime);
        this.workingHours = Objects.requireNonNull(workingHours);
        this.overtimeHours = Objects.requireNonNull(overtimeHours);
        this.breakTime = Objects.requireNonNull(breakTime);
        this.currentTime = Objects.requireNonNull(currentTime);
    }

    public Date getDate() {
        return date;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public EndTime getEndTime() {
        return endTime;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public OvertimeHours getOvertimeHours() {
        return overtimeHours;
    }

    public BreakTime getBreakTime() {
        return breakTime;
    }

    public CurrentTime getCurrentTime() {
        return currentTime;
    }
}
