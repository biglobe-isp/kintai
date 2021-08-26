package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.TimeUnits;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeInterval;
import lombok.NonNull;
import lombok.Value;

@Value
public class AttendanceTimeInterval {
    @NonNull
    TimeInterval interval;

    // TODO: 9:00以降の出勤はクビ（Exception）にする

    public TimeLength intersect(RegulatedBreakTimeInterval regulatedBreakTimeInterval, TimeUnits unit) throws Exception {
        switch(this.interval.getComparedStatus(regulatedBreakTimeInterval.getInterval())) {
            case WITHIN:
                return between(unit);
            case CONTAIN_ALL:
                return regulatedBreakTimeInterval.between(unit);
            case EQUAL_OR_BEFORE:
                return TimeInterval.between(regulatedBreakTimeInterval.getInterval().getStartTimePoint(), this.interval.getEndTimePoint(), unit);
            case EQUAL_OR_AFTER:
                return TimeInterval.between(this.interval.getStartTimePoint(), regulatedBreakTimeInterval.getInterval().getEndTimePoint(), unit);
            case OUT_OF:
                return new TimeLength(0, unit);
            default:
                throw new Exception("時間間隔の重なり取得に失敗しました。");
        }
    }

    public TimeLength between(TimeUnits unit) {
        return this.interval.between(unit);
    }

}
