package org.example.domain

import spock.lang.Specification

import java.time.LocalDateTime

class WorkTimeSpec extends Specification{
    def "仕事の開始時間と終了時間から勤務時間と残業時間を取得"() {
        given:
        def workInformation = FixtureWorkInformation.get()

        expect:
        workInformation.workTime.totalWorkingHours.getValue() == 5 * 60
        workInformation.workTime.totalOverTimeHours.getValue() == 0 * 60
        workInformation.dateToRegister.getValue().toString() == "2023-02-05"
        workInformation.timestampOfTheRegistration.getValue() == LocalDateTime.of(2023, 2, 5, 10, 0)
    }
}
