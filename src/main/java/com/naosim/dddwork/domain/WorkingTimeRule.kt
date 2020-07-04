package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.vo.*
import java.time.Duration

data class WorkingTimeRule(
        val scheduledWorkingTimeSpan: WorkingTimeRange.ScheduledWorkingTimeSpan =
                WorkingTimeRange.ScheduledWorkingTimeSpan(Duration.ofHours(8L)),
        val lunchBreakBorder: Time = Time.of(Hour(12), Minute(0)),
        val nightBreakBorder: Time = Time.of(Hour(18), Minute(0)),
        val midnightBreakBorder: Time = Time.of(Hour(21), Minute(0))
)
