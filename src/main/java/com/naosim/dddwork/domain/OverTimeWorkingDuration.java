package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.rules.RegularWorkingDurationRule;
import lombok.Getter;

import java.time.Duration;

public class OverTimeWorkingDuration {
    @Getter
    private Duration overTimeWorkingDuration;

    public OverTimeWorkingDuration(WorkingDurationExceptBreak workingDurationExceptBreak) {
        overTimeWorkingDuration = workingDurationExceptBreak.getWorkingDurationExceptBreak()
                .toMinutes() > RegularWorkingDurationRule.getRegularWorkingMinutes() ?
                workingDurationExceptBreak.getWorkingDurationExceptBreak()
                        .minus(Duration.ofMinutes(RegularWorkingDurationRule.getRegularWorkingMinutes())) : Duration.ofMinutes(
                0);
    }

    public OverTimeWorkingDuration(Duration overTimeWorkingDuration) {
        this.overTimeWorkingDuration = overTimeWorkingDuration;
    }
}
