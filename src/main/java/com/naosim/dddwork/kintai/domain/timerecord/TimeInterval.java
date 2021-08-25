package com.naosim.dddwork.kintai.domain.timerecord;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.CONTAIN_ALL;
import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.EQUAL_OR_AFTER;
import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.EQUAL_OR_BEFORE;
import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.OUT_OF;
import static com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus.WITHIN;

public interface TimeInterval {
    // TODO: メソッド名の再考
    public ZonedTimePoint getStartTimePoint();
    public ZonedTimePoint getEndTimePoint();

    // 引数の時刻がインターバルの中に入っているか
    default boolean contains(ZonedTimePoint timePoint) {
        if (timePoint == null) {
            return false;
        }
        if (timePoint.isEqualOrAfter(this.getStartTimePoint()) && timePoint.isEqualOrBefore(this.getEndTimePoint())) {
            return true;
        }
        return false;
    }

    // 引数のインターバルを内包しているか
    default boolean containsAll(TimeInterval interval) {
        if (interval == null) {
            return false;
        }
        if (this.contains(interval.getStartTimePoint()) && this.contains(interval.getEndTimePoint())) {
            return true;
        }
        return false;
    }

    // 引数のインターバルに内包されているか
    default boolean within(TimeInterval interval) {
        if (interval == null) {
            return false;
        }
        if (interval.contains(this.getStartTimePoint()) && interval.contains(this.getEndTimePoint())) {
            return true;
        }
        return false;
    }

    // 両者のインターバルを比較した状態を返す
    default TimeIntervalComparedStatus getComparedStatus(TimeInterval comparison) {
        // 引数のインターバルを内包している状態
        if (this.containsAll(comparison)) {
            return CONTAIN_ALL;
        }
        // 引数のインターバルに内包されている状態
        if (this.within(comparison)) {
            return WITHIN;
        }
        // 部分的に被っていて開始時間がはみ出ている
        if (!comparison.contains(this.getStartTimePoint())
                && comparison.contains(this.getEndTimePoint())) {
            return EQUAL_OR_BEFORE;
        }
        // 部分的に被っていて終了時間がはみ出ている
        if (comparison.contains(this.getStartTimePoint())
                && !comparison.contains(this.getEndTimePoint())) {
            return EQUAL_OR_AFTER;
        }
        // 全く被っていない
        return OUT_OF;
    }

    default int between(TimeUnits unit) {
        return between(this.getStartTimePoint(), this.getEndTimePoint(), unit);
    }

    static int between(ZonedTimePoint start, ZonedTimePoint end, TimeUnits unit) {
        if (start == null || end == null || unit == null) return 0;
        return (int)unit.getChronoUnit().between(start.getZonedDateTime(), end.getZonedDateTime());
    }

    // TODO: valueobjectにする
    default int intersect(TimeInterval comparison, TimeUnits unit) {
        switch(this.getComparedStatus(comparison)) {
            case WITHIN:
                return this.between(unit);
            case CONTAIN_ALL:
                return comparison.between(unit);
            case EQUAL_OR_BEFORE:
                return between(comparison.getStartTimePoint(), this.getEndTimePoint(), unit);
            case EQUAL_OR_AFTER:
                return between(this.getStartTimePoint(), comparison.getEndTimePoint(), unit);
            case OUT_OF:
                return 0;
            default:
                // TODO: エラーハンドリング
                return 0;
        }
    }
}
