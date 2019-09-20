package com.naosim.dddwork.domain.rules;

public class RegularWorkingDurationRule {
    private static final int REGULAR_WORKING_HOURS = 8;
    private static final int REGULAR_WORKING_MINUTES = 0;

    public static int getRegularWorkingMinutes() {
        return REGULAR_WORKING_HOURS * 60 + REGULAR_WORKING_MINUTES;
    }
}
