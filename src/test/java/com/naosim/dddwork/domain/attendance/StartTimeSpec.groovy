package com.naosim.dddwork.domain.attendance

import com.naosim.dddwork.domain.TimeUnit
import com.naosim.dddwork.domain.WorkRegulationsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(locations = ["classpath:context.xml"])
class StartTimeSpec extends Specification{

    @Autowired
    private WorkRegulationsRepository workRegulationsRepository

    @Unroll
    def "遅刻判断"() {
        setup:
        def startTime = StartTime.of(TimeUnit.of(hours, minutes))
        def workRegulations = workRegulationsRepository.getCurrentRegulations()

        expect:
        expectedValue == startTime.isLateness(workRegulations)

        where:
        hours | minutes || expectedValue
        9     | 0       || false
        9     | 1       || true
        8     | 59      || false
    }
}
