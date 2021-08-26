package com.naosim.dddwork.kintai.domain.timerecord.regulation;

import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.TimeUnits;
import lombok.NonNull;
import lombok.Value;

@Value
public class RegulatedBreakTimeInterval {
    @NonNull
    TimeInterval interval;

    public TimeLength between(TimeUnits unit) {
        return this.interval.between(unit);
    }
}
