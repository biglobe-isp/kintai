package jp.co.biglobe.kintai.domain

import spock.lang.Specification
import spock.lang.Unroll

class WorkTimeCardBuilderSpec extends Specification{


    @Unroll
    def "タイムカード統計処理のテストgetMonthlyWorkTimCard"(){

        setup:
        def sut = new WorkTimeCardBuilder()

        when:
        sut.punch("20181001",workTime)
        sut.punch("20181002",workTime2)
        Optional<MonthlyWorkTimeCard> actual = sut.build()

        then:
        actual == Optional.of(expected)

        where:
        workTime                |workTime2               ||expected
        new DailyReport(100,200)|new DailyReport(300,400)||new MonthlyWorkTimeCard(400,600)
        new DailyReport(300,400)|new DailyReport(500,600)||new MonthlyWorkTimeCard(800,1000)

    }

}
