package org.example.datasource

import org.example.App
import org.example.domain.FixtureMonth
import org.example.domain.FixtureWorkInformation
import org.example.service.WorkInformationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = [App])
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
        def month = FixtureMonth.get()
        when:
        def result = workInformationRepository.findByMonth(month)

        then:
        result.isPresent()
        def workInformation = result.get()
        workInformation.size() == 1
        workInformation[0].get().workTime.workingHours.time.getValue() == 5 * 60
        workInformation[0].get().workTime.overtime.time.getValue() == 0 * 60
        workInformation[0].get().targetDate.date.getValue() == "20230205"
        workInformation[0].get().createTimestamp.timestamp.getValue() == LocalDateTime.of(2023, 2, 5, 10, 0)
    }
}
