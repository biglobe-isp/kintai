package org.example.domain;

import java.time.LocalTime;
import java.util.List;

public class BreakTimes {
    public static final LocalTime lunchBreakStart = LocalTime.of(12, 0);
    public static final LocalTime lunchBreakEnd = LocalTime.of(13, 0);
    public static final LocalTime eveningBreakStart = LocalTime.of(18, 0);
    public static final LocalTime eveningBreakEnd = LocalTime.of(19, 0);
    public static final LocalTime nightBreakStart = LocalTime.of(21, 0);
    public static final LocalTime nightBreakEnd = LocalTime.of(22, 0);
    public static final LocalTime afternoonBreakStart = LocalTime.of(15, 0);
    public static final LocalTime afternoonBreakEnd = LocalTime.of(16, 0);

    private static final BreakTime LUNCH_BREAK = new BreakTime(lunchBreakStart, lunchBreakEnd);
    private static final BreakTime EVENING_BREAK = new BreakTime(eveningBreakStart, eveningBreakEnd);
    private static final BreakTime NIGHT_BREAK = new BreakTime(nightBreakStart, nightBreakEnd);
    private static final BreakTime AFTERNOON_BREAK = new BreakTime(afternoonBreakStart, afternoonBreakEnd);

    private static final List<BreakTime> ALL_BREAK = List.of(
            LUNCH_BREAK,
            EVENING_BREAK,
            NIGHT_BREAK,
            AFTERNOON_BREAK
    );

    public static List<BreakTime> getBreakTimes() {
        return ALL_BREAK;
    }
}