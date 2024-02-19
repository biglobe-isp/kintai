package com.kintai.datasource.value

import spock.lang.Specification

class TotalMonthSpec extends Specification {
    def "正常系 デフォルトコンストラクタ"() {
        when:
        TotalMonth totalMonth = new TotalMonth("202402")

        then:
        "202402" == totalMonth.getTotalMonth()
    }

    def "正常系"() {
        when:
        WorkDate workDate = new WorkDate("20240101")
        TotalMonth actualTotalMonth = new TotalMonth(workDate)

        then: "勤務日(yyyyMMdd)から勤務月(yyyyMM)のみ切り取られた値が出力されていること"
        "202401" == actualTotalMonth.getTotalMonth()
    }
}
