package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.MonthlyNormalWorkingMinutes;
import com.naosim.dddwork.domain.MonthlyOverworkingMinutes;

public record AggregationResult(MonthlyOverworkingMinutes overworkingMinutes,
                                MonthlyNormalWorkingMinutes normalWorkingMinutes) {
}
