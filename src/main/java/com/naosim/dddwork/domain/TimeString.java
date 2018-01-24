package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TimeString {

    @Getter
    private final String value;

    public Time getTime() {
        return new Time(
                Integer.valueOf(this.value.substring(0, 2)),
                Integer.valueOf(this.value.substring(2, 4))
        );

    }
}
