package com.kintai.datasource.value

import spock.lang.Specification
import spock.lang.Unroll

class OverWorkMinutesSpec extends Specification {
    @Unroll
    def "createOverWorkMinutes正常系(#description)"() {
        setup:
        WorkMinutes actualWorkMinutes = new WorkMinutes(workMinutes)
        OverWorkMinutes actualOverWorkMinutes = new OverWorkMinutes(actualWorkMinutes)

        expect:
        overWorkMinutes == actualOverWorkMinutes.getOverWorkMinutes()

        where:
        workMinutes || overWorkMinutes || description
        480 | 0 || "残業なし"
        540 | 60 || "残業1時間"
        495 | 15 || "残業15分"
        510 | 30 || "残業30分"
        525 | 45 || "残業45分"
    }

    def "totalOverWorkMinutes正常系"() {
        when:
        OverWorkMinutes overWorkMinutes1 = new OverWorkMinutes(180)
        OverWorkMinutes overWorkMinutes2 = new OverWorkMinutes(240)
        List<OverWorkMinutes> overWorkMinutesList = Arrays.asList(overWorkMinutes1, overWorkMinutes2);

        OverWorkMinutes testOverWorkMinutes = new OverWorkMinutes(overWorkMinutesList)

        then: "集計された残業時間が登録されていること"
        420 == testOverWorkMinutes.getOverWorkMinutes()

    }
}
