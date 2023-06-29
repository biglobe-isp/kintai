package jp.co.biglobe.isp.kintai.service.monthly_accumulated_hour

import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.FixtureMonthlyAccumulatedWorkMinutes
import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.FixtureMonthlyWorkRecord
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MonthlyAccumulatedHourReferenceServiceSpec extends Specification{
    private MonthlyWorkRecordRepository monthlyWorkRecordRepository = Mock()
    @Subject
    private MonthlyAccumulatedHourReferenceService service;

    def "create MonthlyAccumulatedWorkMinutes"() {
        when:
        def actual = service.refer("202301")

        then:
        1 * monthlyWorkRecordRepository.refer(_) >> FixtureMonthlyWorkRecord.get()
        actual == FixtureMonthlyAccumulatedWorkMinutes.get()
    }
}
