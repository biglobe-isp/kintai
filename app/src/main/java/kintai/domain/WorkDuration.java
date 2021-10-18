package kintai.domain;

import lombok.Value;

import java.time.Duration;

/**
 * 労働時間.
 */
@Value
public class WorkDuration {
    Duration duration;

    /**
     * 残業時間を計算する.
     *
     * @return 残業時間
     */
    public OverWorkDuration calculateOverWorkDuration() {
        int REGULAR_FULL_TIME_DURATION = 8;

        if (duration.compareTo(Duration.ofHours(REGULAR_FULL_TIME_DURATION)) < 0) {
            return new OverWorkDuration(Duration.ofHours(0));
        }
        return new OverWorkDuration(duration.minusHours(REGULAR_FULL_TIME_DURATION));
    }
}
