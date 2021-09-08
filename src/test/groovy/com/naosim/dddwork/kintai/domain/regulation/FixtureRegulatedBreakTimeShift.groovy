package com.naosim.dddwork.kintai.domain.regulation


import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift

class FixtureRegulatedBreakTimeShift {
    static RegulatedBreakTimeShift get() {
        new RegulatedBreakTimeShift(FixtureRegulatedBreakTimeInterval.getIntervals())
    }
}
