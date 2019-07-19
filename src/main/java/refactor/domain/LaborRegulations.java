package refactor.domain;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class LaborRegulations {
    public static List<BreakTime> getBreakTimeList() {
        return Arrays.asList(
                new BreakTime(
                        new StartTime(LocalTime.of(12, 0)),
                        new EndTime(LocalTime.of(13, 0))),
                new BreakTime(
                        new StartTime(LocalTime.of(18, 0)),
                        new EndTime(LocalTime.of(19, 0))),
                new BreakTime(
                        new StartTime(LocalTime.of(21, 0)),
                        new EndTime(LocalTime.of(22, 0)))
        );
    }

    public static StandardWorkingTime getStandardWorkingTime() {
        return new StandardWorkingTime(8 * 60);
    }
}
