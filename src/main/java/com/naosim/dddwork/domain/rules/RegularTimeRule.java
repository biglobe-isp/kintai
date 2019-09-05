package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import lombok.Getter;

import java.time.Duration;

public class RegularTimeRule {
    private static final int REGULAR_START_HOUR = 9;
    private static final int REGULAR_START_MINUTE = 0;
    private static final int REGULAR_WORKING_HOURS = 8;
    private static final int REGULAR_WORKING_MINUTES = 0;
    @Getter
    private static final EntryTime regularStartTime = new EntryTime(
            new Hour(REGULAR_START_HOUR),
            new Minute(REGULAR_START_MINUTE)
    );
    @Getter
    private static final Duration workingDuration = Duration.ofHours(REGULAR_WORKING_HOURS)
            .plus(Duration.ofMinutes(REGULAR_WORKING_MINUTES));

    public static int getRegularWorkingMinutes() {
        return REGULAR_WORKING_HOURS * 60 + REGULAR_WORKING_MINUTES;
    }
}
