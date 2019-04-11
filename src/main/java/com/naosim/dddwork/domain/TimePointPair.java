package com.naosim.dddwork.domain;

import lombok.Value;

@Value
public class TimePointPair {

    private final TimePoint startTime;
    private final TimePoint endTime;
}
