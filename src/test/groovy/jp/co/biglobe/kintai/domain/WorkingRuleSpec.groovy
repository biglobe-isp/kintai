package jp.co.biglobe.kintai.domain

import jp.co.biglobe.kintai.domain.breaktime.BreakTime
import jp.co.biglobe.kintai.domain.breaktime.DailyBreakTimes
import jp.co.biglobe.kintai.util.Time
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WorkingRuleSpec extends Specification {


    def "就業規則による休憩時間を加味した勤務時間の算出:#testcase"() {
        setup:
        def workDate = new WorkDate("20181018")
        def startWorkTime = new StartWorkTime(start)
        def endWorkTime = new EndWorkTime(end)
        def nowTime = new NowTime("20181003")
        def time = new WorkTime(workDate, startWorkTime, endWorkTime, 0, 0, nowTime)
        def breakTime1 = new BreakTime(new Time("1200"), new Time("1300"))
        def breakTime2 = new BreakTime(new Time("1800"), new Time("1900"))
        def breakTime3 = new BreakTime(new Time("2100"), new Time("2200"))
        def breakTimes = new DailyBreakTimes()
        breakTimes.add(breakTime1)
        breakTimes.add(breakTime2)
        breakTimes.add(breakTime3)

        def workTime = WorkingRule.getInstance().calculateWorkTime(time, breakTimes)

        expect:
        workTime.minutes == ex_minites

        where:
        testcase | start  | end    | ex_minites
        "成功"     | "0900" | "1800" | 480
        "午後"     | "1200" | "2000" | 360
        "1日"     | "0900" | "2400" | 720

    }
}
