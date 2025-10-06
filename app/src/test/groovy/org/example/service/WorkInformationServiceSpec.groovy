package org.example.service

import org.example.domain.FixtureWorkInformation
import org.example.domain.WorkInformation
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.YearMonth

@Unroll
class WorkInformationServiceSpec extends Specification{
    private WorkInformationRepository workInformationRepository = Mock()
    @Subject
    private WorkInformationService service = new WorkInformationService(workInformationRepository)

    def "勤務情報を登録できる"() {
        setup:
        WorkInformation workInformation = FixtureWorkInformation.get()

        when:
        service.register(workInformation)

        then:
        1 * workInformationRepository.persist({it == workInformation})
    }

    def "特定の月の就業時間と残表時間を取得できる"() {
        setup:
        def yearMonth = YearMonth.parse("2023-02")
        def workInformationList = new ArrayList<Optional<WorkInformation>>()
        workInformationList.add(Optional.of(FixtureWorkInformation.get()))
        workInformationList.add(Optional.of(FixtureWorkInformation.get()))

        when:
        def result = service.getTotalWorkTime(yearMonth)

        then:
        1 * workInformationRepository.findByMonth(_) >> Optional.of(workInformationList)
        result.totalWorkingHours.getValue() == 10 * 60
    }
}