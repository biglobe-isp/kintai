package kintai.domain;

import lombok.Value;

import java.time.Duration;

/**
 * 残業時間.
 */
@Value
public class OverWorkDuration {
    int REGULAR_FULL_TIME_DURATION = 8;

    Duration duration;

    public OverWorkDuration(WorkDuration workDuration) {
        this.duration = calculateDuration(workDuration);
    }

    /**
     * 残業時間を計算する.
     *
     * @param workDuration 労働時間
     * @return 残業時間
     */
    private Duration calculateDuration(WorkDuration workDuration) {
        if (workDuration.getDuration().compareTo(Duration.ofHours(REGULAR_FULL_TIME_DURATION)) < 0) {
            return Duration.ofHours(0);
        }
        return workDuration.getDuration().minusHours(REGULAR_FULL_TIME_DURATION);
    }
}
