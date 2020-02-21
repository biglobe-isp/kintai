package com.naosim.dddwork.domain

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class TimeRangeSpec extends Specification {

    @Unroll
    def "通常の算出"() {
        setup:
        def timeUnitFrom = TimeUnit.of(fromHours, fromMinutes)
        def timeUnitTo = TimeUnit.of(toHours, toMinutes)
        def timeRange = TimeRange.of(timeUnitFrom, timeUnitTo)

        expect:
        expectedValue == timeRange.getRangeMinutes()

        where:
        fromHours | fromMinutes | toHours | toMinutes || expectedValue
        9         | 0           | 18      | 0         || 540
        9         | 0           | 8       | 59        || 0
        0         | 0           | 29      | 59        || 1799
    }
}
