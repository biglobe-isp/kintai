package com.naosim.dddwork.domain;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkRegulation {

    private final TimePoint standardStartTime;
    private final TimePoint minStartTime;
    private final TimePoint maxStartTime;
    private final TimePoint standardEndTime;
    private final TimePoint minEndTime;
    private final TimePoint maxEndTime;
    private final ImmutableList<TimeRange> restTimes;
    private final WorkMinute standardWorkMinute;
}
