package refactor.domain;

import java.util.Objects;

public class WorkingHours {
    private final StartTime startTime;
    private final EndTime endTime;
    private final BreakTime breakTime;

    public WorkingHours(
            StartTime startTime,
            EndTime endTime,
            BreakTime breakTime) {
        this.startTime = Objects.requireNonNull(startTime);
        this.endTime = Objects.requireNonNull(endTime);
        this.breakTime = Objects.requireNonNull(breakTime);
    }

    public int inMinutes() {
        int workMinutes = endTime.inMinutes() - startTime.inMinutes() - breakTime.inMinutes();
        return workMinutes;
    }
}
