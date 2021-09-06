package jp.co.esumit.kintai.domain.summary.target_year_month

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class TargetYearMonthSpock extends Specification {
    def "正常系　バリデーションが正常に通るテスト？"() {

        when:
        new TargetYearMonth(input)

        then:
        noExceptionThrown()

        where:
        input    | _
        "202101" | _
    }

    def "正常系　"() {//別にカバレッジのためにやるまでもないテスト

        when:
        def targetYM = new TargetYearMonth(input)

        then:
        targetYM.getValue() == result

        where:
        input    | result
        "202101" | input
    }

    def "異常系 対象年月が不正値　#label"() {

        when:
        new TargetYearMonth(targetYearMonth)

        then:
        IllegalArgumentException e = thrown()
        e.getMessage() == "対象年月が不正値です。"

        where:
        label          | targetYearMonth
        "YYYY"         | "2021"
        "YYYYmmdd"     | "20210101"
        "Upper number" | "２０２１０１"
    }
}
