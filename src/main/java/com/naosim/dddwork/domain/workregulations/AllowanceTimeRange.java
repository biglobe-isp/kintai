package com.naosim.dddwork.domain.workregulations;

import lombok.Value;

import java.time.LocalTime;

@Value
public class AllowanceTimeRange {
    LocalTime minTime;
    LocalTime maxTime;
}
