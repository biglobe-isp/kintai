package jp.co.biglobe.kintai.domain

import spock.lang.Specification


class WorkingRuleSpec extends Specification {


    def "就業規則による休憩時間を加味した勤務時間の算出"() {
        setup:
        def workDate = new WorkDate(date)
        def startWorkTime = new StartWorkTime(start)
        def endWorkTime = new EndWorkTime(end)
        def workTime = WorkingRule.getInstance().calculateWorkTime(
                workDate, startWorkTime, endWorkTime, "なう")

        expect:
        workTime.minutes == ex_minites

        where:
        date|start|end|ex_minites
        "200001888"|"0900"|"1800"|480
        "200001888"|"0900"|"2000"|540
        "200001888"|"0900"|"2400"|720
        "2000040000000"|"0900"|"2000"|540

   }
}
