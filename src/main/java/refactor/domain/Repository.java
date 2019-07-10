package refactor.domain;

public interface Repository {
    void save(
            Date date,
            StartTime startTime,
            EndTime endTime,
            WorkingHours workingHours,
            OvertimeHours overtimeHours,
            CurrentTime currentTime);
}
