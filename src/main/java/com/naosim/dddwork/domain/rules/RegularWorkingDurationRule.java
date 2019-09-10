package com.naosim.dddwork.domain.rules;

import java.time.Duration;

public class RegularWorkingDurationRule {
    private static final int REGULAR_WORKING_HOURS = 8;
    private static final int REGULAR_WORKING_MINUTES = 0;

    private static int getRegularWorkingMinutes() {
        return REGULAR_WORKING_HOURS * 60 + REGULAR_WORKING_MINUTES;
    }

    public Duration calculateRegularWorkingHours(Duration totalWorkingHours) {
        return totalWorkingHours.toMinutes() > RegularWorkingDurationRule.getRegularWorkingMinutes() ?
                Duration.ofMinutes(RegularWorkingDurationRule.getRegularWorkingMinutes()) : totalWorkingHours;
    }

    public Duration calculateOvertimeWorkingHours(Duration totalWorkingHours) {
        return totalWorkingHours.toMinutes() > RegularWorkingDurationRule.getRegularWorkingMinutes() ?
                totalWorkingHours.minus(Duration.ofMinutes(RegularWorkingDurationRule.getRegularWorkingMinutes())) : Duration
                .ofMinutes(
                        0);
    }
}
