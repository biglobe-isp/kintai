package com.naosim.dddwork.domain.daily_work;

import java.time.Duration;

/**
 * 時間
 * 開始時刻と終了時刻両方設定しないと比較処理できないのは不便に感じる
 * →それぞれ別のオブジェクトにするのもあり？
 * 後々の追加機能実装時に大変そう
 */
public class ClockTimesDuration {
    private Duration timeDifference;

    // ↓引数両方型
    ClockTimesDuration(ClockTime startTime, ClockTime endTime) throws IllegalArgumentException {
        this.timeDifference = Duration.between(new ClockTime(startTime).moment, new ClockTime(endTime).moment);
    }

    ClockTimesDuration(ClockTimesDuration difference) {
        this.timeDifference = difference.timeDifference;
    }

    boolean checkTimeMomentsHoursDifference() {
        return timeDifference.toHours() < 1L;
    }

    void subtractTimeMomentsDifference(long minutes) {
        timeDifference = timeDifference.minusMinutes(minutes);
    }

    public Duration getTimeMomentsDifference() {
        return timeDifference;
    }
}