package jp.co.esumit.kintai.domain.kintai_record

import jp.co.esumit.kintai.domain.kintai_record.registered_time.FixtureRegisteredTime
import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay
import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime
import jp.co.esumit.kintai.domain.kintai_record.time_card.StartTime
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.BreakTimes
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.FixedTime
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class RecorderSpec extends Specification {
    def breakTimes = new BreakTimes();
    def fixedTime = new FixedTime();
    def registeredTime = FixtureRegisteredTime.create();
    def recorder = new Recorder(breakTimes, fixedTime, registeredTime);

    def "recordテスト"() {

        setup:
        def target = new TargetDay(targetDay)
        def start = new StartTime(startTime)
        def end = new EndTime(endTime)
        def expKintaiRecord = FixtureKintaiRecord.get(targetDay, startTime, endTime)

        when:
        def actKintaiRecord = recorder.record(target, start, end)

        then:
        actKintaiRecord == expKintaiRecord

        where:
        targetDay  | startTime | endTime
        "20210901" | "0900"    | "1200"
        "20210901" | "0800"    | "1300"
        "20210901" | "0900"    | "1900"
        "20210901" | "0900"    | "2200"
        "20210901" | "0900"    | "1230"
    }
}
