package jp.co.biglobe.kintai.domain

import spock.lang.Specification
import spock.lang.Unroll

class MonthlyWorkTimeCardSpec extends Specification {

    @Unroll
    def "時間分変換テスト:#testcase"() {
        setup:
        MonthlyWorkTimeCard timeCard = new MonthlyWorkTimeCard(minutus, overminutes)

        expect:
        timeCard.getWorkHours() == workHours
        timeCard.getWorkMinutes() == workMinutes
        timeCard.getOverWorkHours() == overWorkHours
        timeCard.getOverWorkMinutes() == overWorkMinutes

        where:
        testcase | minutus | overminutes || workHours | workMinutes | overWorkHours | overWorkMinutes
        "成功"     | 630     | 303         || 10        | 30          | 5             | 3
        "成功"     | 1000    | 10          || 16        | 40          | 0             | 10
        "ゼロ"     | 0       | 0           || 0         | 0           | 0             | 0
        "閾値"     | 480     | 0           || 8         | 0           | 0             | 0
    }
}
