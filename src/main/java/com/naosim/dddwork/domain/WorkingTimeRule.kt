package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.vo.*
import java.time.Duration

data class WorkingTimeRule(
        val scheduledWorkingTimeSpan: WorkingTimeRange.ScheduledWorkingTimeSpan =
                WorkingTimeRange.ScheduledWorkingTimeSpan(Duration.ofHours(8L)),
        val lunchBreakBorder: Time = Time.of(Hour(12), Minute(0)),
        val nightBreakBorder: Time = Time.of(Hour(18), Minute(0)),
        val midnightBreakBorder: Time = Time.of(Hour(21), Minute(0))
) {
    companion object {
        val FIXED_REST_TIME: WorkingTimeRange.RestTimeSpan = WorkingTimeRange.RestTimeSpan(Duration.ofHours(1L))
    }

    fun evalPastLunchBreakOnPunchedOut(punchedOutTime: PunchOutTime): Boolean = punchedOutTime >= lunchBreakBorder + FIXED_REST_TIME
    fun evalPastNightBreakOnPunchedOut(punchedOutTime: PunchOutTime): Boolean = punchedOutTime >= nightBreakBorder + FIXED_REST_TIME
    fun evalPastMidnightBreakOnPunchedOut(punchedOutTime: PunchOutTime): Boolean = punchedOutTime >= midnightBreakBorder + FIXED_REST_TIME
    fun evalBreakTimeOnPunchedOut(punchedOutTime: PunchOutTime): Boolean = punchedOutTime.hour == lunchBreakBorder.hour
            || punchedOutTime.hour == nightBreakBorder.hour
            || punchedOutTime.hour == midnightBreakBorder.hour

    fun bindToScheduledWorkingTimeSpan(workingTimeSpan: TimeSpan): WorkingTimeRange.ScheduledWorkingTimeSpan =
            WorkingTimeRange.ScheduledWorkingTimeSpan.of(if (workingTimeSpan > scheduledWorkingTimeSpan) {
                scheduledWorkingTimeSpan
            } else {
                workingTimeSpan
            })
}
