package com.naosim.dddwork.domain.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Time {

    @Getter
    private Hour hour;

    @Getter
    private Minute minute;

    public Time(int hour, int minute) {

        this.hour = new Hour(hour);

        if (!this.isCorrectAsMinuteOfTime(minute))
            throw new RuntimeException("時刻として扱うことができる範囲の分を設定してください");
        this.minute = new Minute(minute);
    }

    public static Time convertToTime(String value) {
        return new Time(
                Integer.valueOf(value.substring(0, 2)),
                Integer.valueOf(value.substring(2, 4))
        );
    }

    public int convertTimeToMinutes() {
        return this.hour.getValue() * 60 + this.minute.getValue();
    }

    private boolean isCorrectAsMinuteOfTime(int minute) {
        if (0 <= minute && minute < 60) return true;
        return false;
    }
}
