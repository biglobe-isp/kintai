package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.vo.TimeSpan

data class TotalWorkingTimeSummary (
        val totalWorkingTimeSpan: TimeSpan,
        val totalExtraWorkingTimeSpan: TimeSpan
)
