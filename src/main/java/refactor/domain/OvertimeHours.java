package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class OvertimeHours {
    @NonNull
    private final WorkingHours workingHours;

    public int inMinutes() {
        return Math.max(workingHours.inMinutes() - 8 * 60, 0);
    }
}
