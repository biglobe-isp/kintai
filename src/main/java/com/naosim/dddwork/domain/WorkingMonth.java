package com.naosim.dddwork.domain;

import java.time.YearMonth;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class WorkingMonth {
    @Getter
    private final YearMonth value;
}
