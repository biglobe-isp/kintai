package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class StartingHours {
    @Getter
    private final LocalTime value;

    public StartingHoursMinutes getStartingHoursMinutes() {
        return new StartingHoursMinutes(this.value.getHour() * 60 + this.value.getMinute());
    }
}
