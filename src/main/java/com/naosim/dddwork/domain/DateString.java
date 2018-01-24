package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class DateString {

    @Getter
    private final String value;

    public boolean isTargetYearMonth(YearMonth targetYearMonth) {
        return value.startsWith(targetYearMonth.getValue());
    }
}
