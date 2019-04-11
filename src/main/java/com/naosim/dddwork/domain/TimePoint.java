package com.naosim.dddwork.domain;

import lombok.Value;

import java.time.LocalTime;

@Value
public class TimePoint {

    private final LocalTime value;
}
