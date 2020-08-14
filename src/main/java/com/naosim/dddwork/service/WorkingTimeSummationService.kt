package com.naosim.dddwork.service

import com.naosim.dddwork.domain.*
import com.naosim.dddwork.domain.vo.*
import java.time.LocalDateTime

class WorkingTimeSummationService(
        private val workingTimeRepository: WorkingTimeRepository,
        private val workingTimeRule: WorkingTimeRule = WorkingTimeRule()
) {
    fun summarize(year: Year, month: Month): TotalWorkingTimeSummary =
            workingTimeRepository.findByYearAndMonth(year, month, workingTimeRule).let {
                TotalWorkingTimeSummary.of(it)
            }
}
