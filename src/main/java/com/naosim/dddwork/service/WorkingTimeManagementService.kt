package com.naosim.dddwork.service

import com.naosim.dddwork.domain.*
import com.naosim.dddwork.domain.vo.*
import java.time.Duration
import java.time.LocalDateTime

class WorkingTimeManagementService(
        private val workingTimeRepository: WorkingTimeRepository,
        private val workingTimeRule: WorkingTimeRule = WorkingTimeRule()
) {
    fun punch(workingDate: WorkingDate, punchInTime: PunchInTime, punchOutTime: PunchOutTime) {
        workingTimeRepository.save(WorkingTimeRecord(
                workingDate = workingDate,
                workingTimeRange = WorkingTimeRange.of(
                        punchInTime = punchInTime,
                        punchOutTime = punchOutTime,
                        rule = workingTimeRule
                ),
                punchedDateTime = PunchedDateTime(LocalDateTime.now())
        ))
    }

    fun total(year: Year, month: Month): TotalWorkingTimeSummary = workingTimeRepository.findByYearAndMonth(year, month, workingTimeRule)
            .fold(TotalWorkingTimeSummary(TimeSpan(Duration.ZERO), TimeSpan(Duration.ZERO))) { acc, it ->
                TotalWorkingTimeSummary(
                        totalWorkingTimeSpan = TimeSpan(acc.totalWorkingTimeSpan.value + it.workingTimeRange.value),
                        totalExtraWorkingTimeSpan = TimeSpan(acc.totalExtraWorkingTimeSpan.value + it.workingTimeRange.extraWorkingTimeSpan.value)
                )
            }
}
