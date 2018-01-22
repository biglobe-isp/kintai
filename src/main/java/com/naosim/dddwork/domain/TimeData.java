package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class TimeData {

    @Getter
    private int hour;

    @Getter
    private int minute;

    public TimeData(int hour, int minute) {

        if (this.isCorrectAsHour(hour) && this.isCorrectAsMinute(minute)) {
            this.hour = hour;
            this.minute = minute;
        } else {
            throw new RuntimeException("正しい時間を設定してください");
        }
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
