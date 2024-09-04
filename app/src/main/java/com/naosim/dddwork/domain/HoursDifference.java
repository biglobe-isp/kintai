package com.naosim.dddwork.domain;

import java.time.temporal.ChronoUnit;

public class HoursDifference {
    private final long hourDifference;

    HoursDifference(TimeMoment startTime, TimeMoment endTime) {
        this.hourDifference = ChronoUnit.HOURS.between(new TimeMoment(startTime).moment, new TimeMoment(endTime).moment);
    }

    public boolean CheckHourDifference() {
        return hourDifference >= 1L;
    }
}