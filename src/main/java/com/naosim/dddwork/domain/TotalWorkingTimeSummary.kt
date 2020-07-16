package com.naosim.dddwork.domain

data class TotalWorkingTimeSummary (
        val totalWorkingTimeSpan: WorkingTimeRange.ActualWorkingTimeSpan,
        val totalExtraWorkingTimeSpan: WorkingTimeRange.ExtraWorkingTimeSpan
)
