package com.naosim.dddwork.kintai.domain.timerecord;

import lombok.NonNull;
import lombok.Value;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.CONTAIN_ALL;
import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.EQUAL_OR_AFTER;
import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.EQUAL_OR_BEFORE;
import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.OUT_OF;
import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.WITHIN;

@Value
public class TimeInterval {
    @NonNull
    StartTime startTime;
    @NonNull
    EndTime endTime;

    public TimeInterval(StartTime startTime, EndTime endTime) {
        if (startTime.getTimePoint().isAfter(endTime.getTimePoint())) {
            throw new IllegalArgumentException("開始時刻が終了時刻より後になっています。");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ZonedTimePoint getStartTimePoint() {
        return this.startTime.getTimePoint();
    }

    public ZonedTimePoint getEndTimePoint() {
        return this.endTime.getTimePoint();
    }

    public int getStartHour() {
        return this.startTime.getTimePoint().getHour();
    }

    public int getStartMinute() {
        return this.startTime.getTimePoint().getMinute();
    }

    // 両者のインターバルを比較した状態を返す
    public TimeIntervalComparedStatus getComparedStatus(TimeInterval comparison) {
        // 引数のインターバルを内包している状態
        if (this.containsAll(comparison)) {
            return CONTAIN_ALL;
        }
        // 引数のインターバルに内包されている状態
        if (this.within(comparison)) {
            return WITHIN;
        }
        // 部分的に被っていて開始時間がはみ出ている
        if (!comparison.contains(this.startTime.getTimePoint())
                && comparison.contains(this.endTime.getTimePoint())) {
            return EQUAL_OR_BEFORE;
        }
        // 部分的に被っていて終了時間がはみ出ている
        if (comparison.contains(this.startTime.getTimePoint())
                && !comparison.contains(this.endTime.getTimePoint())) {
            return EQUAL_OR_AFTER;
        }
        // 全く被っていない
        return OUT_OF;
    }

    public TimeLength between(TimeUnits unit) {
        return between(this.startTime.getTimePoint(), this.endTime.getTimePoint(), unit);
    }

    public static TimeLength between(ZonedTimePoint start, ZonedTimePoint end, TimeUnits unit) {
        if (start == null || end == null || unit == null) {
            return new TimeLength(0, unit);
        }
        return new TimeLength(
                unit.getChronoUnit().between(start.getZonedDateTime(), end.getZonedDateTime()),
                unit
        );
    }

    // 引数の時刻がインターバルの中に入っているか
    private boolean contains(ZonedTimePoint timePoint) {
        if (timePoint == null) {
            return false;
        }
        if (timePoint.isEqualOrAfter(this.startTime.getTimePoint()) && timePoint.isEqualOrBefore(this.endTime.getTimePoint())) {
            return true;
        }
        return false;
    }

    // 引数のインターバルを内包しているか
    private boolean containsAll(TimeInterval interval) {
        if (interval == null) {
            return false;
        }
        if (this.contains(interval.startTime.getTimePoint()) && this.contains(interval.endTime.getTimePoint())) {
            return true;
        }
        return false;
    }

    // 引数のインターバルに内包されているか
    private boolean within(TimeInterval interval) {
        if (interval == null) {
            return false;
        }
        if (interval.contains(this.startTime.getTimePoint()) && interval.contains(this.endTime.getTimePoint())) {
            return true;
        }
        return false;
    }
}
