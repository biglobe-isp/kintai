package jp.co.esumit.kintai.domain.kintai_record.working_minutes

import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime
import jp.co.esumit.kintai.domain.kintai_record.time_card.StartTime
import jp.co.esumit.kintai.domain.kintai_record.time_card.TimeCard
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.BreakTimes
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.FixedTime
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WorkingMinutesSpec extends Specification {
    def "正常系　終了時間が#endTime"() {

        setup:
        def stampingTimes = new TimeCard(new StartTime(startTime), new EndTime(endTime))

        when:
        def workingMinutes = new WorkingMinutes(stampingTimes, new BreakTimes(), new FixedTime())

        then:
        workingMinutes.getActualWorkingMinutes().getValue() == actualWorkingMinutes
        workingMinutes.getOvertimeMinutes().getValue() == overtimeMinutes

        where:
        startTime | endTime | actualWorkingMinutes | overtimeMinutes
        "0900"    | "1800"  | 420                  | 0
        "0900"    | "2000"  | 480                  | 0
        "0900"    | "2100"  | 540                  | 60
        "0900"    | "2300"  | 600                  | 120
    }
}
