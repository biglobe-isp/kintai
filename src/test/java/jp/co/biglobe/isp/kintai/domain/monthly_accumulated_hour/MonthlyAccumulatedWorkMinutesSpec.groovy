package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MonthlyAccumulatedWorkMinutesSpec extends Specification{
    def "Return correct AccumulatedWorkRecordMinutes from test csv"() {
        setup:
        def monthlyAccumulatedWorkMinutes = MonthlyAccumulatedWorkMinutes.from(FixtureMonthlyWorkRecord.get())

        when:
        def actual = monthlyAccumulatedWorkMinutes.getAccumulatedWorkRecordMinutes()

        then:
        actual == FixtureMonthlyAccumulatedWorkMinutes.AccumulatedWorkRecordMinutesFromTestCsv()

    }
    def "Return correct AccumulatedOverTimeMinutes from test csv"() {
        setup:
        def monthlyAccumulatedWorkMinutes = MonthlyAccumulatedWorkMinutes.from(FixtureMonthlyWorkRecord.get())

        when:
        def actual = monthlyAccumulatedWorkMinutes.getAccumulatedOverTImeMinutes()

        then:
        actual == FixtureMonthlyAccumulatedWorkMinutes.AccumulatedOverTimeMinutesFromTestCsv()
    }
}
