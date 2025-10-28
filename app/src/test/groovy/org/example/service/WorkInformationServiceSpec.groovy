package org.example.service

import org.example.domain.FixtureTotalHours
import org.example.domain.FixtureWorkInformation
import org.example.domain.RegisterInput
import org.example.domain.FixtureRegisterInput
import org.example.domain.TotalHours
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
        RegisterInput registerInput = FixtureRegisterInput.get()

        when:
        service.register(registerInput)

        then:
        1 * workInformationRepository.persist(wi ->{
            assert wi instanceof WorkInformation
            assert wi.getWorkTime().getWorkStartTime() == registerInput.toWorkInformation().getWorkTime().getWorkStartTime()
            assert wi.getWorkTime().getWorkEndTime() == registerInput.toWorkInformation().getWorkTime().getWorkEndTime()
            assert wi.getTotalHours().getTotalWorkingHours() == registerInput.toWorkInformation().getTotalHours().getTotalWorkingHours()
            assert wi.getTotalHours().getTotalOverTimeHours() == registerInput.toWorkInformation().getTotalHours().getTotalOverTimeHours()
        })
    }

    def "特定の月の就業時間と残表時間を取得できる"() {
        setup:
        def yearMonth = "202302"
        def totalHoursList = new ArrayList<Optional<TotalHours>>()
        totalHoursList.add(Optional.of(FixtureTotalHours.get()))
        totalHoursList.add(Optional.of(FixtureTotalHours.get()))

        when:
        def result = service.getTotalWorkTime(yearMonth)

        then:
        1 * workInformationRepository.findByMonth(_) >> Optional.of(totalHoursList)
        result.totalWorkingHours.getValue() == 8 * 60
    }
}