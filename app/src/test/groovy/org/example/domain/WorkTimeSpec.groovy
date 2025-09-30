package org.example.domain

import spock.lang.Specification

import java.time.LocalDateTime

class WorkTimeSpec extends Specification{
    def "仕事の開始時間と終了時間から勤務時間と残業時間を取得"() {
        given:
        def workTime = FixtureWorkTime.get()
        def createTimestamp = FixtureCreateTimestamp.get()
        def targetDate = FixtureTargetDate.get()
        def workInformation = FixtureWorkInformation.get()

        expect:
        workInformation.workTime.workingHours.time.getValue() == 5 * 60
        workInformation.workTime.overtime.time.getValue() == 0 * 60
        workInformation.targetDate.date.getValue() == "20230205"
        workInformation.createTimestamp.timestamp.getValue() == LocalDateTime.of(2023, 2, 5, 10, 0)
    }
}
