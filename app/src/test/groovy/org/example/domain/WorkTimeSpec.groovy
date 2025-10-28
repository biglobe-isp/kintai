package org.example.domain

import spock.lang.Specification

import java.time.LocalDateTime
import java.time.LocalTime

class WorkTimeSpec extends Specification{
    def "仕事の開始時間と終了時間から勤務時間と残業時間を取得"() {
        given:
        def workInformation = FixtureWorkInformation.get()

        expect:
        workInformation.workTime.workStartTime.getValue() == LocalTime.of(12, 30)
        workInformation.workTime.workEndTime.getValue() == LocalTime.of(18, 30)
        workInformation.totalHours.totalWorkingHours.getValue() == 4 * 60
        workInformation.totalHours.totalOverTimeHours.getValue() == 0 * 60
        workInformation.dateToRegister.getValue().toString() == "2023-02-05"
        workInformation.timestampOfTheRegistration.getValue() == LocalDateTime.of(2023, 2, 5, 10, 0)
    }
}
