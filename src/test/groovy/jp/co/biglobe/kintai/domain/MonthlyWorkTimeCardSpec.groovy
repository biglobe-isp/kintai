package jp.co.biglobe.kintai.domain

import spock.lang.Specification
import spock.lang.Unroll

class MonthlyWorkTimeCardSpec extends Specification {

    @Unroll
    def "ゲッターテスト"(){
        setup:
        MonthlyWorkTimeCard timeCard = new MonthlyWorkTimeCard(minutus, overminutes)

        expect:
        timeCard.getWorkHours() == workHours
        timeCard.getWorkMinutes() == workMinutes
        timeCard.getOverWorkHours() == overWorkHours
        timeCard.getOverWorkMinutes() == overWorkMinutes

        where:
        minutus|overminutes||workHours|workMinutes|overWorkHours|overWorkMinutes
        630    |        303||       10|         30|            5|              3
        1000   |         10||       16|         40|            0|             10
        0      |          0||        0|          0|            0|              0
        480    |          0||        8|          0|            0|              0
    }
}
