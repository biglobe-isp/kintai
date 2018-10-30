package jp.co.biglobe.kintai.domain

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WorkingRuleSpec extends Specification {


    def "就業規則による休憩時間を加味した勤務時間の算出"() {
        setup:
        def workDate = new WorkDate(date)
        def startWorkTime = new StartWorkTime(start)
        def endWorkTime = new EndWorkTime(end)
        def nowTime = new NowTime(now)
        def time = new WorkTime(workDate,startWorkTime,endWorkTime,0,0,nowTime)
        def workTime = WorkingRule.getInstance().calculateWorkTime(time)

        expect:
        workTime.minutes == ex_minites

        where:
        date           |start |end   |now       |ex_minites
        "200001888"    |"0900"|"1800"|"20181003"|       480
        "200001888"    |"0900"|"2000"|"20181003"|       540
        "200001888"    |"0900"|"2400"|"20181003"|       720
        "2000040000000"|"0900"|"2000"|"20181003"|       540

   }
}
