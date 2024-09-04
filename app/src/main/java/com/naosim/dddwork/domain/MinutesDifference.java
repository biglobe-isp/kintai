package com.naosim.dddwork.domain;

import java.time.temporal.ChronoUnit;

class MinutesDifference {
    private long timeDifference;

    MinutesDifference(TimeMoment startTime, TimeMoment endTime) {
        this.timeDifference = ChronoUnit.MINUTES.between(new TimeMoment(startTime).moment, new TimeMoment(endTime).moment);
    }

    MinutesDifference(MinutesDifference difference) {
        this.timeDifference = difference.timeDifference;
    }

    boolean ValidateMinutesDifference() {
        return timeDifference <= 0L;
    }

    void SubtractMinutesDifference(long minutes) {
        timeDifference -= minutes;
    }

    long GetMinutesDifference() {
        return timeDifference;
    }
}