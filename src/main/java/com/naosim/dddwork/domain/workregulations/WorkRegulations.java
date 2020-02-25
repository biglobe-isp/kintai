package com.naosim.dddwork.domain.workregulations;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeRange;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WorkRegulations {
    StartTimeRange startTimeRange;
    EndTimeRange endTimeRange;
    BreakTimes breakTimes;

    public int getStandardWorkingMinutes() {
        // TODO:標準時間から勤務時間を求める
        TimePoint timePointFrom = TimePoint.of(this.startTimeRange.getStandard());
        TimePoint timePointTo = TimePoint.of(this.endTimeRange.getStandard());
        TimeRange timeRange = TimeRange.of(timePointFrom, timePointTo);
        return timeRange.getRangeMinutes();
    }
}
