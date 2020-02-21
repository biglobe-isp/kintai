package com.naosim.dddwork.domain.workregulations

import com.naosim.dddwork.domain.TimeUnit
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime

@SpringBootTest
class AllowanceTimeRageSpec extends Specification {

    @Unroll
    def "大小比較"() {
        setup:
        def allowanceTimeRange = AllowanceTimeRange.of(
                LocalTime.of(9, 0),
                LocalTime.of(18, 0))

        expect:
        expectedValue == allowanceTimeRange.isOutOfRange(TimeUnit.of(hours, minutes))

        where:
        hours | minutes || expectedValue
        9     | 00      || false
        9     | 01      || false
        8     | 59      || true
        18    | 00      || false
        18    | 01      || true
        17    | 59      || false
    }
}
