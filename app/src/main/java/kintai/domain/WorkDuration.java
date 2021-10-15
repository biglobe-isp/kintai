package kintai.domain;

import lombok.Value;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 労働時間.
 */
@Value
public class WorkDuration {
    int START_TIME = 9;
    int REGULAR_END_TIME = 18;
    int LUNCH_BREAK_START_TIME = 12;
    int LUNCH_BREAK_END_TIME = 13;
    int NIGHT_BREAK_START_TIME = 18;
    int NIGHT_BREAK_END_TIME = 19;
    int MID_NIGHT_BREAK_START_TIME = 21;
    int MID_NIGHT_BREAK_END_TIME = 22;
    int BREAK_DURATION = 60;

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
