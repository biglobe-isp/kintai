package jp.co.biglobe.isp.kintai.domain.attendance_record

import jp.co.biglobe.isp.kintai.domain.work_regulation.FixtureWorkRegulation
import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class WorkRecordMinutesSpec extends Specification {

    def "workRecordMinutes: #label " () {
        when:
        def actual = WorkRecordMinutes.from(workTime, FixtureWorkRegulation.get())

        then:
        actual == expected

        where:
        label           |   workTime                                   ||   expected
        "JustTime"      |   FixtureWorkTime.JustTime()                 ||   FixtureWorkRecordMinutes.JustTime()
        "8:00 - 18:00"  |   FixtureWorkTime.EarlyOpeningTime()         ||   FixtureWorkRecordMinutes.EarlyOpeningTime()
        "9:00 - 12:00"  |   FixtureWorkTime.EarlyClosingTimeAt1200()   ||   FixtureWorkRecordMinutes.EarlyClosingTimeAt1230()
        "9:00 - 12:30"  |   FixtureWorkTime.EarlyClosingTimeAt1230()   ||   FixtureWorkRecordMinutes.EarlyClosingTimeAt1230()
        "9:00 - 13:00"  |   FixtureWorkTime.EarlyClosingTimeAt1300()   ||   FixtureWorkRecordMinutes.EarlyClosingTimeAt1300()
        "9:00 - 13:30"  |   FixtureWorkTime.EarlyClosingTimeAt1330()   ||   FixtureWorkRecordMinutes.EarlyClosingTimeAt1330()
        "9:00 - 18:30"  |   FixtureWorkTime.LateClosingTimeAt1830()    ||   FixtureWorkRecordMinutes.LateClosingTimeAt1830()
        "9:00 - 19:30"  |   FixtureWorkTime.LateClosingTimeAt1930()    ||   FixtureWorkRecordMinutes.LateClosingTimeAt1930()
        "9:00 - 21:00"  |   FixtureWorkTime.LateClosingTimeAt2100()    ||   FixtureWorkRecordMinutes.LateClosingTimeAt2100()
        "9:00 - 21:30"  |   FixtureWorkTime.LateClosingTimeAt2130()    ||   FixtureWorkRecordMinutes.LateClosingTimeAt2130()
        "9:00 - 22:00"  |   FixtureWorkTime.LateClosingTimeAt2200()    ||   FixtureWorkRecordMinutes.LateClosingTimeAt2200()
        "9:00 - 22:30"  |   FixtureWorkTime.LateClosingTimeAt2230()    ||   FixtureWorkRecordMinutes.LateClosingTimeAt2230()
        "9:00 - 23:00"  |   FixtureWorkTime.LateClosingTimeAt2300()    ||   FixtureWorkRecordMinutes.LateClosingTimeAt2300()
    }
}
