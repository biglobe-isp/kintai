package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.vo.Month
import com.naosim.dddwork.domain.vo.Year

interface WorkingTimeRepository {
    fun save(workingTimeRecord: WorkingTimeRecord)
    fun findByYearAndMonth(year: Year, month: Month, workingTimeRule: WorkingTimeRule = WorkingTimeRule()): List<WorkingTimeRecord>
}
