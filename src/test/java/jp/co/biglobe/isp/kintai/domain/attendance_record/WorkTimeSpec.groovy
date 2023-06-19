package jp.co.biglobe.isp.kintai.domain.attendance_record

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime;

@Unroll
class WorkTimeSpec extends Specification {
    def "getWorkTimeMinutes: #label "() {
        when:
        def actual = sut.getWorkTimeMinutes()

        then:
        actual == expected

        where:
        label                | sut                                       || expected
        "RegulatedJustTime"  | FixtureWorkTime.JustTime()                || FixtureWorkTimeMinutes.regulatedJustTime()
        "EarlyOpeningTime"   | FixtureWorkTime.EarlyOpeningTime()        || FixtureWorkTimeMinutes.regulatedEarlyOpeningTime()
        "EarlyClosingTime"   | FixtureWorkTime.EarlyClosingTimeAt1200()  || FixtureWorkTimeMinutes.EarlyClosingTimeAt1200()
        "LateClosingTime"    | FixtureWorkTime.LateClosingTimeAt1900()   || FixtureWorkTimeMinutes.LateClosingTimeAt1900()
    }

    def "Invalid WorkTime: #label "() {
        when:
        new WorkTime(LocalTime.of(openingHour, openingMinutes), LocalTime.of(closingHour,closingMinutes))

        then:
        Exception e = thrown()
        e.getMessage() == expected

        where:
        label                                        | openingHour | openingMinutes | closingHour | closingMinutes  || expected
        "openingTime is after regulatedOpeningTime"  | 9           | 1              | 18          | 0               || "勤務開始時刻は9:00より前に設定してください. でないとクビです."
        "closingTime is after Next Day"              | 9           | 0              | 24          | 0               || "Invalid value for HourOfDay (valid values 0 - 23): 24"
    }
}
