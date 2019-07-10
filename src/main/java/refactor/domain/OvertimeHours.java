package refactor.domain;

import java.util.Objects;

public class OvertimeHours {
    private final WorkingHours workingHours;

    public OvertimeHours(WorkingHours workingHours) {
        this.workingHours = Objects.requireNonNull(workingHours);
    }

    public int inMinuts() {
        return Math.max(workingHours.inMinutes() - 8 * 60, 0);
    }
}
