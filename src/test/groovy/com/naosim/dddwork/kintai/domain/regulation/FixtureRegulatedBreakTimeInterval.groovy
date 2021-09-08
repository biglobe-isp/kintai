package com.naosim.dddwork.kintai.domain.regulation


import com.naosim.dddwork.kintai.domain.WrappedTimeInterval
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeInterval

class FixtureRegulatedBreakTimeInterval {
    static List<RegulatedBreakTimeInterval> getIntervals() {
        Arrays.asList(
                WrappedTimeInterval.get("1200", "1300"),
                WrappedTimeInterval.get("1800", "1900"),
                WrappedTimeInterval.get("2100", "2200")
        ) as List<RegulatedBreakTimeInterval>
    }
}
