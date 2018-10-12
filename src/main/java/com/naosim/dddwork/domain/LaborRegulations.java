package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class LaborRegulations {
    @Getter
    private final FixedTime fixedTime;
    @Getter
    private final BreakTimeList breakTimeList;
}
