package com.naosim.dddwork.domain

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime

@SpringBootTest
class TimeRangeSpec extends Specification {

    @Unroll
    def "通常の算出"() {
        setup:
        def timeRange = TimeRange.of(
                TimePoint.of(fromHours, fromMinutes),
                TimePoint.of(toHours, toMinutes))

        expect:
        expectedValue == timeRange.getRangeMinutes()

        where:
        fromHours | fromMinutes | toHours | toMinutes || expectedValue
        9         | 0           | 18      | 0         || 540
        9         | 0           | 8       | 59        || 0
        0         | 0           | 29      | 59        || 1799
    }

    @Unroll
    def "大小比較"() {
        setup:
        def timeRange = TimeRange.of(
                TimePoint.of(9, 0),
                TimePoint.of(18, 0))

        expect:
        expectedValue == timeRange.isOutOfRange(LocalTime.of(hours, minutes))

        where:
        hours | minutes || expectedValue
        9     | 00      || true
        9     | 01      || false
        8     | 59      || true
        18    | 00      || true
        18    | 01      || true
        17    | 59      || false
    }
}
