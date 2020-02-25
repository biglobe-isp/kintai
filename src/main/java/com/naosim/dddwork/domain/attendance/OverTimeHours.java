package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

@Value
public class OverTimeHours {
    TimeUnit timeUnit;

    public static OverTimeHours of(WorkingHours workingHours, WorkRegulations workRegulations) {
        return new OverTimeHours(workingHours, workRegulations);
    }

    private OverTimeHours(WorkingHours workingHours, WorkRegulations workRegulations) {
        int overTimeMinutes = workingHours.getTimeUnit().getTotalMinutes() - workRegulations.getStandardWorkingMinutes();

        if (overTimeMinutes > 0) {
            this.timeUnit = TimeUnit.of(overTimeMinutes);
        } else {
            this.timeUnit = TimeUnit.of(0);
        }
    }
}
