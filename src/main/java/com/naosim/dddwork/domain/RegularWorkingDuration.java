package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.rules.RegularWorkingDurationRule;

import java.time.Duration;

public class RegularWorkingDuration {
    private Duration regularWorkingDuration;

    public Duration getRegularWorkingDuration() {
        return regularWorkingDuration;
    }

    public RegularWorkingDuration(WorkingDurationExceptBreak workingDurationExceptBreak) {
        regularWorkingDuration = workingDurationExceptBreak.getWorkingDurationExceptBreak()
                .toMinutes() > RegularWorkingDurationRule.getRegularWorkingMinutes() ?
                Duration.ofMinutes(RegularWorkingDurationRule.getRegularWorkingMinutes()) : workingDurationExceptBreak.getWorkingDurationExceptBreak();
    }

    public RegularWorkingDuration(Duration regularWorkingDuration) {
        this.regularWorkingDuration = regularWorkingDuration;
    }
}
