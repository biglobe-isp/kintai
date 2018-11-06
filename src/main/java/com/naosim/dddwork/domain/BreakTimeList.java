package com.naosim.dddwork.domain;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class BreakTimeList {
    @Getter
    private final List<BreakTime> value;
}
