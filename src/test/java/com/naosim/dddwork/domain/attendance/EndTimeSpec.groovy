package com.naosim.dddwork.domain.attendance

import com.naosim.dddwork.domain.TimePoint
import com.naosim.dddwork.domain.WorkRegulationsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime

@ContextConfiguration(locations = ["classpath:context.xml"])
class EndTimeSpec extends Specification {

    @Autowired
    private WorkRegulationsRepository workRegulationsRepository

    @Unroll
    def "早退判断"() {
        setup:
        def endTime = EndTime.of(TimePoint.of(hours, minutes))
        def workRegulations = workRegulationsRepository.getCurrentRegulations()

        expect:
        expectedValue == endTime.isLeaveEarly(workRegulations)

        where:
        hours | minutes || expectedValue
        18    | 1       || false
        18    | 0       || false
        17    | 59      || true
    }

    @Unroll
    def "みなし終了時刻取得"() {
        setup:
        def endTime = EndTime.of(TimePoint.of(hours, minutes))

        expect:
        expectedValue == endTime.getDeemedEndTime()

        where:
        hours | minutes || expectedValue
        23    | 59      || TimePoint.of(23, 59)
        24    | 0       || TimePoint.of(24, 0)
        24    | 1       || TimePoint.of(24, 0)
        27    | 59      || TimePoint.of(24, 0)
    }
}
