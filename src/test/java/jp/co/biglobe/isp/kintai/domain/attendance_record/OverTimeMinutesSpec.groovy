package jp.co.biglobe.isp.kintai.domain.attendance_record

import jp.co.biglobe.isp.kintai.domain.work_regulation.FixtureRegulatedWorkTimeMinutes
import jp.co.biglobe.isp.kintai.domain.work_regulation.FixtureWorkRegulation
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime

@Unroll
class OverTimeMinutesSpec extends Specification {
    def "overTimeMinutes: #label " () {
        setup:
        def workRecordHours = WorkRecordMinutes.from(workTime, FixtureWorkRegulation.get())

        when:
        def actual = OverTimeMinutes.from(workRecordHours, FixtureRegulatedWorkTimeMinutes.get())

        then:
        actual == expected

        where:
        label           |   workTime                                   ||   expected
        "JustTime"      |   FixtureWorkTime.JustTime()                 ||   FixtureOverTimeMinutes.JustTime()
        "8:00 - 18:00"  |   FixtureWorkTime.EarlyOpeningTime()         ||   FixtureOverTimeMinutes.EarlyOpeningTime()
        "9:00 - 12:00"  |   FixtureWorkTime.EarlyClosingTimeAt1200()   ||   FixtureOverTimeMinutes.EarlyClosingTimeAt1200()
        "9:00 - 18:30"  |   FixtureWorkTime.LateClosingTimeAt1830()    ||   FixtureOverTimeMinutes.LateClosingTimeAt1830()
        "9:00 - 19:00"  |   FixtureWorkTime.LateClosingTimeAt1900()    ||   FixtureOverTimeMinutes.LateClosingTimeAt1900()
        "9:00 - 19:30"  |   FixtureWorkTime.LateClosingTimeAt1930()    ||   FixtureOverTimeMinutes.LateClosingTimeAt1930()
        "9:00 - 21:00"  |   FixtureWorkTime.LateClosingTimeAt2100()    ||   FixtureOverTimeMinutes.LateClosingTimeAt2100()
        "9:00 - 21:30"  |   FixtureWorkTime.LateClosingTimeAt2130()    ||   FixtureOverTimeMinutes.LateClosingTimeAt2130()
        "9:00 - 22:00"  |   FixtureWorkTime.LateClosingTimeAt2200()    ||   FixtureOverTimeMinutes.LateClosingTimeAt2200()
        "9:00 - 22:30"  |   FixtureWorkTime.LateClosingTimeAt2230()    ||   FixtureOverTimeMinutes.LateClosingTimeAt2230()
    }
}