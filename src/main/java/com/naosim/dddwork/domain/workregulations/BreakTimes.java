package com.naosim.dddwork.domain.workregulations;

import com.naosim.dddwork.domain.TimeRange;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class BreakTimes {
    List<TimeRange> list;
}
