package com.naosim.dddwork.domain.workregulations

import com.naosim.dddwork.domain.service.WorkRegulationsGeneratable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class WorkRegulationsSpec extends Specification {

    @Autowired
    private WorkRegulationsGeneratable workRegulationsRepository

    def "標準勤務時間"() {
        setup:
        def workRegulations = workRegulationsRepository.getCurrentRegulations()

        when:
        int diff = workRegulations.getStandardWorkingMinutes()

        then:
        diff == 540
    }
}
