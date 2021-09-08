package com.naosim.dddwork.kintai.domain.actualtime

import com.naosim.dddwork.kintai.domain.regulation.FixtureRegulatedWorkingTimeMinutes
import com.naosim.dddwork.kintai.domain.timerecord.TimeLength
import com.naosim.dddwork.kintai.domain.timerecord.TimeUnits
import com.naosim.dddwork.kintai.domain.timerecord.actualtime.ActualMinutes
import com.naosim.dddwork.kintai.domain.timerecord.actualtime.overtime.ActualOvertimeMinutes
import com.naosim.dddwork.kintai.domain.timerecord.actualtime.workingtime.ActualWorkingTimeMinutes

class WrappedActualMinutes {
    static ActualMinutes get(workingTimeMinutes) {
        def actualWorkingTimeMinutes = new ActualWorkingTimeMinutes(new TimeLength(workingTimeMinutes, TimeUnits.MINUTES))
        new ActualMinutes(
                actualWorkingTimeMinutes,
                new ActualOvertimeMinutes(actualWorkingTimeMinutes, FixtureRegulatedWorkingTimeMinutes.get()
                )
        )
    }
}
