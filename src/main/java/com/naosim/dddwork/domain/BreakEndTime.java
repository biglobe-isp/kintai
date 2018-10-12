package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class BreakEndTime {
    @Getter
    private final LocalTime value;

    public BreakEndTimeMinutes getBreakEndTimeMinutes() {
        return new BreakEndTimeMinutes(this.value.getHour() * 60 + this.value.getMinute());
    }

}
