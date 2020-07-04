package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.vo.PunchedDateTime
import com.naosim.dddwork.domain.vo.WorkingDate

data class WorkingTimeRecord(
        val workingDate: WorkingDate,
        val workingTimeRange: WorkingTimeRange,
        val punchedDateTime: PunchedDateTime
)
