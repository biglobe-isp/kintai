package kintai.domain

import spock.lang.Specification

import java.time.Duration

class OverWorkDurationSpec extends Specification {
    def "労働時間から残業時間を算出する・通常勤務"() {
        setup:
        def workDuration = FixtureWorkDuration.getFullTime();

        when:
        def overWorkDuration = new OverWorkDuration(workDuration)

        then:
        overWorkDuration.getDuration() == Duration.ofHours(0)
    }

    def "労働時間から残業時間を算出する・残業あり"() {
        setup:
        def workDuration = FixtureWorkDuration.getOverTime();

        when:
        def overWorkDuration = new OverWorkDuration(workDuration)

        then:
        overWorkDuration.getDuration() == Duration.ofHours(3)
    }

    def "労働時間から残業時間を算出する・時短勤務"() {
        setup:
        def workDuration = FixtureWorkDuration.getShortTime();

        when:
        def overWorkDuration = new OverWorkDuration(workDuration)

        then:
        overWorkDuration.getDuration() == Duration.ofHours(0)
    }

}
