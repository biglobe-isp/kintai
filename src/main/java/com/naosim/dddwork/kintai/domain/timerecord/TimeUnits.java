package com.naosim.dddwork.kintai.domain.timerecord;

import java.time.temporal.ChronoUnit;

public enum TimeUnits {
    MINUTES(ChronoUnit.MINUTES),
    HOURS(ChronoUnit.HOURS);

    private final ChronoUnit chronoUnit;

    private TimeUnits(ChronoUnit chronoUnit) {
        this.chronoUnit = chronoUnit;
    }

    public ChronoUnit getChronoUnit() {
        return this.chronoUnit;
    }
}
