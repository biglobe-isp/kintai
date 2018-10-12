package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class FixedTime {
    @Getter
    private final StartingTime startingTime;
    @Getter
    private final ClosingTime closingTime;
    @Getter
    private final WorkingTime workingTime;
}
