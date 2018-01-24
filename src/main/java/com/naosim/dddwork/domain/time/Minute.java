package com.naosim.dddwork.domain.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Minute {

    @Getter
    private final int value;

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
