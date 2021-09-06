package jp.co.esumit.kintai.service


import jp.co.esumit.kintai.config.FixtureAppProperties
import jp.co.esumit.kintai.domain.kintai_record.FixtureKintaiRecordList
import jp.co.esumit.kintai.domain.repository.KintaiRepository
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MonthlySummaryServiceSpec extends Specification {
    def repository = Mock(KintaiRepository)
    def service = new MonthlySummaryService(repository)

    def "getRecordList検証"() {
        setup:
        def targetYearMonth = new TargetYearMonth(targetYm)
        //CSVを置き換え
        FixtureAppProperties.get()

        def expKintaiRecordList = FixtureKintaiRecordList.get(targetYm);

        when:
        service.getRecordList(targetYearMonth);

        then:
        1 * repository.read(*_) >> { TargetYearMonth actTargetYm ->
            assert actTargetYm == expTargetYm
            expKintaiRecordList
        }

        where:
        targetYm || expTargetYm
        "202101" || new TargetYearMonth(targetYm)
        "202201" || new TargetYearMonth(targetYm)
    }
}

