package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class WorkingHours {
    @NonNull
    private final StartTime startTime;
    @NonNull
    private final EndTime endTime;

    public ActualWorkingHours exclude(List<BreakTime> breakTimeList) {
        TimeZone workingTimeZone = new TimeZone(startTime, endTime);

        int totalBreakTime =
                breakTimeList.stream().
                        map(breakTime -> workingTimeZone.overlap(
                                new TimeZone(breakTime.getStartTime(), breakTime.getEndTime()))).
                        filter(Optional::isPresent).
                        mapToInt(timeZone -> timeZone.get().minutes()).
                        sum();

        return new ActualWorkingHours(workingTimeZone.minutes() - totalBreakTime);
    }
}
