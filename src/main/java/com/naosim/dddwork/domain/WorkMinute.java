package com.naosim.dddwork.domain;

import lombok.Value;

@Value
public class WorkMinute {

    private final int value;

    public static WorkMinute of(int value) {
        return new WorkMinute(value);
    }

    public static WorkMinute parse(String value) {
        return WorkMinute.of(Integer.parseInt(value));
    }
}
