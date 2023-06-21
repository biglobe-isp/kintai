package jp.co.biglobe.isp.kintai.datasource

import jp.co.biglobe.isp.kintai.config.FixtureAppProperties
import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.FixtureMonthlyWorkRecord
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MonthlyWorkRecordRepositorySpec extends Specification{
    def "Return WorkRecordMap specified yearMonth"() {
        setup:
        def properties = FixtureAppProperties.get()
        def yearMonth = "202301"
        def monthlyWorkRecordRepositoryCsv = new MonthlyWorkRecordRepositoryCsv(properties)

        when:
        def actual = monthlyWorkRecordRepositoryCsv.refer(yearMonth)

        then:
        actual == FixtureMonthlyWorkRecord.get()
    }
}
