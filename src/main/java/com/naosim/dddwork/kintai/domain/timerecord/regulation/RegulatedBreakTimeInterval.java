package com.naosim.dddwork.kintai.domain.timerecord.regulation;

import com.naosim.dddwork.kintai.domain.timerecord.EndTime;
import com.naosim.dddwork.kintai.domain.timerecord.StartTime;
import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.ZonedTimePoint;
import lombok.NonNull;
import lombok.Value;

@Value
public class RegulatedBreakTimeInterval implements TimeInterval {
    @NonNull
    StartTime startTime;
    @NonNull
    EndTime endTime;

    @Override
    public ZonedTimePoint getStartTimePoint() {
        return this.startTime.getTimePoint();
    }

    @Override
    public ZonedTimePoint getEndTimePoint() {
        return this.endTime.getTimePoint();
    }
}
