package com.naosim.dddwork.domain.daily_work;

import java.time.Duration;

/**
 * 時刻の差分
 */
public class TimeMomentsDifference {
    private Duration timeDifference;

    TimeMomentsDifference(TimeMoment startTime, TimeMoment endTime) {
        this.timeDifference = Duration.between(new TimeMoment(startTime).moment, new TimeMoment(endTime).moment);
    }

    TimeMomentsDifference(TimeMomentsDifference difference) {
        this.timeDifference = difference.timeDifference;
    }

    boolean validateTimeMomentsDifference() {
        return timeDifference.toMinutes() <= 0L;
    }

    void subtractTimeMomentsDifference(long minutes) {
        timeDifference = timeDifference.minusMinutes(minutes);
    }

    public Duration getTimeMomentsDifference() {
        return timeDifference;
    }
}