package org.example.domain;

import java.util.List;

public class BreakTimes {
    private static final BreakTime LUNCH_BREAK = new BreakTime(12 * 60, 13 * 60);
    private static final BreakTime EVENING_BREAK = new BreakTime(18 * 60, 19 * 60);
    private static final BreakTime NIGHT_BREAK = new BreakTime(21 * 60, 22 * 60);

    private static final List<BreakTime> ALL_BREAK = List.of(
            LUNCH_BREAK,
            EVENING_BREAK,
            NIGHT_BREAK
    );

    public static List<BreakTime> getAllBreak() {
        return ALL_BREAK;
    }
}