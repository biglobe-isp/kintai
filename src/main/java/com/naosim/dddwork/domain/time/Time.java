package com.naosim.dddwork.domain.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Time {

    // TODO: クラス化（minuteについてはMinuteクラスを利用できそう）
    @Getter
    private int hour;

    @Getter
    private int minute;

    public Time(int hour, int minute) {

        if (this.isCorrectAsHour(hour) && this.isCorrectAsMinute(minute)) {
            this.hour = hour;
            this.minute = minute;
        } else {
            throw new RuntimeException("正しい時間を設定してください");
        }
    }

    public static Time convertToTime(String value) {
        return new Time(
                Integer.valueOf(value.substring(0, 2)),
                Integer.valueOf(value.substring(2, 4))
        );
    }

    public int convertTimeToMinutes() {
        return this.hour * 60 + this.minute;
    }

    private boolean isCorrectAsHour(int hour) {
        if (0 <= hour && hour < 24) return true;
        return false;
    }

    private boolean isCorrectAsMinute(int minute) {
        if (0 <= minute && minute < 60) return true;
        return false;
    }
}
