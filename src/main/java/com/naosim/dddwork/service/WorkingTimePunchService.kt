package com.naosim.dddwork.service

import com.naosim.dddwork.domain.*
import com.naosim.dddwork.domain.vo.*
import java.time.LocalDateTime

class WorkingTimePunchService(
        private val workingTimeRepository: WorkingTimeRepository,
        private val workingTimeRule: WorkingTimeRule = WorkingTimeRule()
) {
    fun punch(workingDate: WorkingDate, punchInTime: PunchInTime, punchOutTime: PunchOutTime) {
        workingTimeRepository.save(WorkingTimeRecord(
                workingDate = workingDate,
                // 本来は勤怠記録時に規程は必要ないが、今回の保存ファイルの仕様上ここで渡す必要がある
                workingTimeRange = workingTimeRule.calcWorkingTimeRange(
                        punchInTime = punchInTime,
                        punchOutTime = punchOutTime
                ),
                punchedDateTime = PunchedDateTime(LocalDateTime.now())
        ))
    }
}
