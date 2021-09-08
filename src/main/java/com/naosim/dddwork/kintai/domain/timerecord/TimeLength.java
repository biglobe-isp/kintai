package com.naosim.dddwork.kintai.domain.timerecord;

import lombok.NonNull;
import lombok.Value;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;
import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.HOURS;

@Value
public class TimeLength {

    long length;
    @NonNull
    TimeUnits unit;

    public TimeLength(long length, TimeUnits unit) {
        if (length < 0) {
            throw new IllegalArgumentException("時間の長さにマイナスの値は入れられません。");
        }
        this.length = length;
        this.unit = unit;
    }

    public TimeLength add(TimeLength addition) {
        if (!isSameUnit(addition)) {
            throw new IllegalArgumentException("単位が違う時間を加算することはできません。");
        }
        return new TimeLength(this.length + addition.length, this.unit);
    }

    public TimeLength subtract(TimeLength subtraction) {
        if (!isSameUnit(subtraction)) {
            throw new IllegalArgumentException("単位が違う時間を減算することはできません。");
        }
        return new TimeLength(this.length - subtraction.length, this.unit);
    }

    public boolean isLonger(TimeLength comparison) {
        if (!isSameUnit(comparison)) {
            throw new IllegalArgumentException("単位が違う時間を比較することはできません。");
        }
        return this.length > comparison.length;
    }

    public TimeLength minuteToHour() {
        return new TimeLength(this.length / 60, HOURS);
    }

    //HACK: 名前が微妙・・・
    public TimeLength extractRemainderMinutes() {
        return new TimeLength(this.length % 60, MINUTES);
    }

    private boolean isSameUnit(TimeLength comparison) {
        return this.unit.equals(comparison.unit);
    }

}
