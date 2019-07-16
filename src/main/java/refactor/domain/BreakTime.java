package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class BreakTime {
    @Getter
    @NonNull
    private final StartTime startTime;
    @Getter
    @NonNull
    private final EndTime endTime;
}
