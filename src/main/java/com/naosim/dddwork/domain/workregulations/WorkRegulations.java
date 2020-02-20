package com.naosim.dddwork.domain.workregulations;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WorkRegulations {
    StartTimeRange startTimeRange;
    EndTimeRange endTimeRange;
    BreakTimes breakTimes;
}
