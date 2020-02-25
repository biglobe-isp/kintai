package com.naosim.dddwork.domain.workregulations;

import com.naosim.dddwork.domain.TimeRange;
import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;

@Builder
@Value
public class StartTimeRange {
    LocalTime standard;
    TimeRange range;
}
