package com.naosim.dddwork.domain.kintai.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Minute {

    @Getter
    private final int value;

    public Minute(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
