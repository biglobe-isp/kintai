package com.naosim.dddwork.domain;

import java.time.LocalTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class ClosingHours {
    @Getter
    private final LocalTime value;

    public ClosingHoursMinutes getClosingHoursMinutes() {
        return new ClosingHoursMinutes(this.value.getHour() * 60 + this.value.getMinute());
    }
}
