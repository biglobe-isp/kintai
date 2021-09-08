package com.naosim.dddwork.kintai.domain.regulation

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength
import com.naosim.dddwork.kintai.domain.timerecord.TimeUnits
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes

class FixtureRegulatedWorkingTimeMinutes {
    static get() {
        new RegulatedWorkingTimeMinutes(new TimeLength(480, TimeUnits.MINUTES))
    }
}
