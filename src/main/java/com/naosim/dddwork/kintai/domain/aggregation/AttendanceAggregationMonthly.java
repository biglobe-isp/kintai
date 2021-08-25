package com.naosim.dddwork.kintai.domain.aggregation;

import lombok.Value;

import java.util.Optional;

@Value
public class AttendanceAggregationMonthly {
    Optional<Integer> totalWorkingTimeMinutes;
    Optional<Integer> totalOvertimeMinutes;
}
