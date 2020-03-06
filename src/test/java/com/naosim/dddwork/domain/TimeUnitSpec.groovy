package com.naosim.dddwork.domain

import spock.lang.Specification
import spock.lang.Unroll

class TimeUnitSpec extends Specification{

    @Unroll
    def "通常"() {
        setup:
        def timeUnit = TimeUnit.of(hours, minutes)

        expect:
        expectedHour == timeUnit.getHour()
        expectedminutes == timeUnit.getMinutes()

        where:
        hours | minutes || expectedHour | expectedminutes
        1     | 59      || 1            | 59
        1     | 60      || 2            | 0
        1     | 90      || 2            | 30
        1     | 180     || 4            | 0
    }

    @Unroll
    def "分のみ"() {
        setup:
        def timeUnit = TimeUnit.of(minutes)

        expect:
        expectedHour == timeUnit.getHour()
        expectedminutes == timeUnit.getMinutes()

        where:
        minutes || expectedHour | expectedminutes
        60      || 1            | 0
        90      || 1            | 30
        350     || 5            | 50
    }
}
