package jp.co.biglobe.kintai.domain

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MonthlyWorkTimeCardSpec extends Specification {

    def "タイムカード統計処理のテスト"(){

        setup:
        def workTime = new WorkTime(100,200)
        def workTime2 = new WorkTime(300,400)

        def timeCard = new MonthlyWorkTimeCard()

        when:
        timeCard.punch("20181001",workTime)
        timeCard.punch("20181002",workTime2)

        def result = timeCard.getTotalMinutes()

        then:
        result == 400

    }
}
