package com.naosim.dddwork.domain

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class TimeRangeSpec extends Specification {

    @Unroll
    def "通常の算出"() {
        setup:
        def timeUnitFrom = new TimeUnit(inputTimeFrom)
        def timeUnitTo = new TimeUnit(inputTimeTo)
        def timeRange = new TimeRange(timeUnitFrom, timeUnitTo)

        expect:
        expectedValue == timeRange.getRangeMinutes()

        where:
        inputTimeFrom | inputTimeTo || expectedValue
        "0900"        | "1800"      || 540
        "0900"        | "0859"      || 0
        "0000"        | "2959"      || 1799
    }
}
