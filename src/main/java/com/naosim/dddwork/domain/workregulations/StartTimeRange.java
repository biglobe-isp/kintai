package com.naosim.dddwork.domain.workregulations;

import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;

@Builder
@Value
public class StartTimeRange {
    LocalTime standard;
    AllowanceTimeRange range;
}
