package com.naosim.dddwork.service

import com.naosim.dddwork.domain.*
import com.naosim.dddwork.domain.vo.*
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
            .fold(TotalWorkingTimeSummary(WorkingTimeRange.ActualWorkingTimeSpan.ZERO, WorkingTimeRange.ExtraWorkingTimeSpan.ZERO)) { acc, it ->
                TotalWorkingTimeSummary(
                        totalWorkingTimeSpan = acc.totalWorkingTimeSpan + it.workingTimeRange.actualWorkingTimeSpan,
                        totalExtraWorkingTimeSpan = acc.totalExtraWorkingTimeSpan + it.workingTimeRange.extraWorkingTimeSpan
                )
            }
}
