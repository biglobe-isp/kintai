package jp.co.biglobe.isp.kintai.service.workrecord_registration

import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import jp.co.biglobe.isp.kintai.domain.work_record.FixtureWorkRecord
import jp.co.biglobe.isp.kintai.domain.work_record.WorkRecordFactory
import jp.co.biglobe.isp.kintai.domain.input_worktime.FixtureInputWorkTime
import jp.co.biglobe.isp.kintai.domain.work_regulation.FixtureWorkRegulation
import jp.co.biglobe.isp.kintai.service.work_regulation.WorkRegulationRepository
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WorkRecordRegistrationServiceSpec extends Specification{
    private WorkRecordRepository workRecordRepository = Mock()
    private WorkRegulationRepository workRegulationRepository = Mock()
    private WorkRecordFactory workRecordFactory = Mock()

    @Subject
    private WorkRecordRegistrationService service

    def "create WorkRecord"() {
        when:
        service.register(FixtureInputWorkTime.get())

        then:
        1 * workRegulationRepository.refer() >> FixtureWorkRegulation.get()
        1 * workRecordFactory.create(FixtureInputWorkTime.get(), FixtureWorkRegulation.get()) >> FixtureWorkRecord.getJustTime()
        1 * workRecordRepository.persist(_) >> {}
    }
}
