package org.example.datasource

import org.example.App

import org.example.domain.FixtureWorkInformation
import org.example.service.WorkInformationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.YearMonth

@SpringBootTest
@ActiveProfiles("test")
class WorkInformationRepositoryDbSpec extends Specification {
    @Autowired
    WorkInformationRepository workInformationRepository

    def "DBに勤務情報を保存できる"() {
        setup:
        def workInformation = FixtureWorkInformation.get()

        when:
        workInformationRepository.persist(workInformation)

        then:
        noExceptionThrown()
    }

    def "DBから勤務情報を取得できる"() {
        setup:
        def month = YearMonth.parse("2023-02")
        when:
        def result = workInformationRepository.findByMonth(month)

        then:
        result.isPresent()
        def totalWork = result.get()
        totalWork.size() == 1
        totalWork[0].get().totalWorkingHours.getValue() == 5 * 60
        totalWork[0].get().totalOverTimeHours.getValue() == 0 * 60
    }
}
