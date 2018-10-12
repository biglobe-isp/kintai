package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class BreakTime {
    @Getter
    private final ApplyStartDate applyStartDate;
    @Getter
    private final ApplyEndDate applyEndDate;
    @Getter
    private final BreakStartTime breakStartTime;
    @Getter
    private final BreakEndTime breakEndTime;
}
