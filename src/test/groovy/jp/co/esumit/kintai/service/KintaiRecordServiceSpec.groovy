package jp.co.esumit.kintai.service

import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord
import jp.co.esumit.kintai.domain.kintai_record.Recorder
import jp.co.esumit.kintai.domain.kintai_record.registered_time.FixtureRegisteredTime
import jp.co.esumit.kintai.domain.kintai_record.target_day.FixtureTargetDay
import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime
import jp.co.esumit.kintai.domain.kintai_record.time_card.FixtureStartTime
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.BreakTimes
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.FixedTime
import jp.co.esumit.kintai.domain.repository.KintaiRepository
import spock.lang.Specification

class KintaiRecordServiceSpec extends Specification {
    def breakTimes = new BreakTimes();
    def fixedTime = new FixedTime();
    def registeredTime = FixtureRegisteredTime.create();
    def repository = Mock(KintaiRepository)
    def recorder = new Recorder(breakTimes, fixedTime, registeredTime);
    def service = new KintaiRecordService(repository, recorder)

    def "persist検証"() {
        setup:
        def targetDay = FixtureTargetDay.of()
        def startTime = FixtureStartTime.StartTime()
        def endTimeClass = new EndTime(endTime)
        def expected = recorder.record(FixtureTargetDay.of(), FixtureStartTime.StartTime(), new EndTime(endTime))
        
        when:
        service.persist(targetDay, startTime, endTimeClass)

        then:
        1 * repository.write(*_) >> { KintaiRecord kintaiRecord ->
            assert kintaiRecord == expected
        }

        where:
        endTime | _
        "1800"  | _
        "2000"  | _
    }
}
