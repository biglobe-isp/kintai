package jp.co.biglobe.isp.kintai.domain.daily

import jp.co.biglobe.isp.kintai.domain.daily.OvertimeMinutes
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class OvertimeMinutesSpec extends Specification {
    def "勤務時間が8時間超過したら超過分を残業として扱う"() {

        expect:
        OvertimeMinutes.calculate(worktime) == overtime

        where:
        worktime                 || overtime
        new WorkTimeMinutes(479) || new OvertimeMinutes(0)
        new WorkTimeMinutes(480) || new OvertimeMinutes(0)
        new WorkTimeMinutes(481) || new OvertimeMinutes(1)
        new WorkTimeMinutes(482) || new OvertimeMinutes(2)
    }
}
