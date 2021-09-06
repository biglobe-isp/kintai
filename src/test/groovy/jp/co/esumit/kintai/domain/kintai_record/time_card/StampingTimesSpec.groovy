package jp.co.esumit.kintai.domain.kintai_record.time_card

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class StampingTimesSpec extends Specification {
    def "正常系 getOperatingMinutes()確認　終了時間が#endTime"() {

        when:
        def endTimeClass = new EndTime(endTime)
        def target = new TimeCard(FixtureStartTime.StartTime(), endTimeClass)

        then:
        target.getOperatingMinutes() == result

        where:
        endTime | result
        "1800"  | (18 - 9) * 60
        "1900"  | (19 - 9) * 60
        "2000"  | (20 - 9) * 60
        "2100"  | (21 - 9) * 60
        "2200"  | (22 - 9) * 60
        "2300"  | (23 - 9) * 60
    }

    def "異常系 終了時刻(#endTime) < 開始時刻(#startTime)"() {

        when:
        def startTimeClass = new StartTime(startTime)
        def endTimeClass = new EndTime(endTime)
        new TimeCard(startTimeClass, endTimeClass)

        then:
        RuntimeException e = thrown()
        e.getMessage() == "終了時刻は開始時刻より後に設定してください。"

        where:
        startTime | endTime
        "1800"    | "0900"
        "0901"    | "0900"
    }
}
