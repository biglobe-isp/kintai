package com.naosim.dddwork.domain.workregulations;

import lombok.Value;

import java.time.LocalTime;

@Value(staticConstructor="of")
public class AllowanceTimeRange {
    LocalTime minTime;
    LocalTime maxTime;
}
