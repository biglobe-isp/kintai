package com.naosim.dddwork.domain.attendance

import com.naosim.dddwork.domain.TimeUnit
import com.naosim.dddwork.domain.WorkRegulationsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(locations = ["classpath:context.xml"])
class EndTimeSpec extends Specification {

    @Autowired
    private WorkRegulationsRepository workRegulationsRepository

    @Unroll
    def "早退判断"() {
        setup:
        def endTime = EndTime.of(TimeUnit.of(hours, minutes))
        def workRegulations = workRegulationsRepository.getCurrentRegulations()

        expect:
        expectedValue == endTime.isLeaveEarly(workRegulations)

        where:
        hours | minutes || expectedValue
        18    | 1       || false
        18    | 0       || false
        17    | 59      || true
    }
}
