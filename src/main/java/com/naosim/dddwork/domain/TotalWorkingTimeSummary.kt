package com.naosim.dddwork.domain

data class TotalWorkingTimeSummary (
        val totalWorkingTimeSpan: WorkingTimeRange.ActualWorkingTimeSpan,
        val totalExtraWorkingTimeSpan: WorkingTimeRange.ExtraWorkingTimeSpan
) {
    companion object {
        fun of(workingTimeRecords: List<WorkingTimeRecord>): TotalWorkingTimeSummary = workingTimeRecords
                .fold(TotalWorkingTimeSummary(WorkingTimeRange.ActualWorkingTimeSpan.ZERO, WorkingTimeRange.ExtraWorkingTimeSpan.ZERO)) { acc, it ->
                    TotalWorkingTimeSummary(
                            totalWorkingTimeSpan = acc.totalWorkingTimeSpan + it.workingTimeRange.actualWorkingTimeSpan,
                            totalExtraWorkingTimeSpan = acc.totalExtraWorkingTimeSpan + it.workingTimeRange.extraWorkingTimeSpan
                    )
                }
    }
}
