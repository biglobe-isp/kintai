package com.naosim.dddwork.domain.daily_work;

import java.time.temporal.ChronoUnit;

public class MinutesDifference {
    private long timeDifference;

    MinutesDifference(TimeMoment startTime, TimeMoment endTime) {
        this.timeDifference = ChronoUnit.MINUTES.between(new TimeMoment(startTime).moment, new TimeMoment(endTime).moment);
    }

    MinutesDifference(MinutesDifference difference) {
        this.timeDifference = difference.timeDifference;
    }

    boolean validateMinutesDifference() {
        return timeDifference <= 0L;
    }

    void subtractMinutesDifference(long minutes) {
        timeDifference -= minutes;
    }

    public long getMinutesDifference() {
        return timeDifference;
    }
}