package com.naosim.dddwork.domain.daily_work;

import java.time.temporal.ChronoUnit;

class HoursDifference {
    private final long hourDifference;

    HoursDifference(TimeMoment startTime, TimeMoment endTime) {
        this.hourDifference = ChronoUnit.HOURS.between(new TimeMoment(startTime).moment, new TimeMoment(endTime).moment);
    }

    public boolean checkHourDifference() {
        return hourDifference >= 1L;
    }
}