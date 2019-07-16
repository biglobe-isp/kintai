package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class WorkingHours {
    @NonNull
    private final StartTime startTime;
    @NonNull
    private final EndTime endTime;
    @NonNull
    private final BreakTime breakTime;

    public int inMinutes() {
        int workMinutes = endTime.inMinutes() - startTime.inMinutes() - breakTime.inMinutes();
        return workMinutes;
    }
}
