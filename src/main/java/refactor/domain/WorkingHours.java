package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

@AllArgsConstructor
public class WorkingHours {
    @NonNull
    private final StartTime startTime;
    @NonNull
    private final EndTime endTime;

    public ActualWorkingHours calculateActualWorkingHours() {
        TimeZone workingTimeZone = new TimeZone(startTime, endTime);

        int totalBreakTime =
                LaborRegulations.getBreakTimeList().stream().
                        map(breakTime -> workingTimeZone.overlap(
                                new TimeZone(breakTime.getStartTime(), breakTime.getEndTime()))).
                        filter(Optional::isPresent).
                        mapToInt(timeZone -> timeZone.get().getMinutes()).
                        sum();

        return new ActualWorkingHours(workingTimeZone.getMinutes() - totalBreakTime);
    }

    public OvertimeHours calculateOvertimeHours() {
        int actualWorkingHours = calculateActualWorkingHours().getMinutes();
        int overtimeHours = Math.max(actualWorkingHours - LaborRegulations.getStandardWorkingTime().getMinutes(), 0);
        return new OvertimeHours(overtimeHours);
    }
}
