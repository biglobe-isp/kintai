package refactor.service;

import refactor.domain.*;

import java.util.Objects;

public class KintaiInputService {
    private final Date date;
    private final StartTime startTime;
    private final EndTime endTime;
    private final CurrentTime currentTime;
    private final Repository repository;

    public KintaiInputService(
            Date date,
            StartTime startTime,
            EndTime endTime,
            CurrentTime currentTime,
            Repository repository) {
        this.date = Objects.requireNonNull(date);
        this.startTime = Objects.requireNonNull(startTime);
        this.endTime = Objects.requireNonNull(endTime);
        this.currentTime = Objects.requireNonNull(currentTime);
        this.repository = Objects.requireNonNull(repository);
    }

    public void inputKintai() {
        BreakTime breakTime = new BreakTime(endTime);
        WorkingHours workingHours = new WorkingHours(startTime, endTime, breakTime);
        OvertimeHours overtimeHours = new OvertimeHours(workingHours);
        repository.save(date, startTime, endTime, workingHours, overtimeHours, currentTime);
    }
}
