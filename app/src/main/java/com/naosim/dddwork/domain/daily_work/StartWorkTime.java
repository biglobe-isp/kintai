package com.naosim.dddwork.domain.daily_work;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

/**
 * 勤務開始時刻
 */
public class StartWorkTime {
    // この部分で9時00分のバリデーションをしているが、所定労働時間は別のドメインにもある
    // もし所定労働時間に依存する場合、循環参照状態となってしまう
    private final LocalTime startWorkTime;
    static final LocalTime SCHEDULED_START_WORK_TIME = LocalTime.of(9, 0);

    public StartWorkTime(LocalTime startWorkTime) {
        validateStartWorkTime(startWorkTime);
        this.startWorkTime = startWorkTime;
    }

    private void validateStartWorkTime(LocalTime startWorkTime) {
        try {
            if (Duration.between(Objects.requireNonNull(startWorkTime),
                            SCHEDULED_START_WORK_TIME)
                    .toMinutes() < 0L)
                throw new IllegalArgumentException("勤務開始時間が9時00分を超えています。遅刻厳禁。");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("勤務開始時刻の値が存在しません");
        }
    }

    public LocalTime getValue() {
        return startWorkTime;
    }
}