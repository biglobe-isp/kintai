package com.naosim.dddwork.domain.workregulations;

import com.naosim.dddwork.domain.TimeRange;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BreakTimes {
    TimeRange lunchBreakTime;
    TimeRange eveningBreakTime;
    TimeRange nightBreakTime;
}
