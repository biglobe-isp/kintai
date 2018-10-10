package jp.co.biglobe.kintai.domain

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WorkTimeCardBuilderSpec extends Specification{


    def "タイムカード統計処理のテストgetMonthlyWorkTimCard"(){

        setup:
        def sut = new WorkTimeCardBuilder()

        when:
        sut.punch("20181001",workTime)
        sut.punch("20181002",workTime2)
        Optional<MonthlyWorkTimeCard> actual = sut.getMonthlyWorkTimCard()

        then:
        actual == Optional.of(expected)

        where:
        workTime|workTime2||expected
        new WorkTime(100,200)|new WorkTime(300,400)||new MonthlyWorkTimeCard(400,600)
        new WorkTime(300,400)|new WorkTime(500,600)||new MonthlyWorkTimeCard(800,1000)

    }

}
