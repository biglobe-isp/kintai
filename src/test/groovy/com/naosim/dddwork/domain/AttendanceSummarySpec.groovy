package com.naosim.dddwork.domain

import io.vavr.collection.List
import spock.lang.Specification

class AttendanceSummarySpec extends Specification{

    def "AttendanceSummary-fired-case1" () {
        setup:
        def attendanceRecord1  = FixtureAttendanceRecord.get(2019,5,1,9,1,18,0)
        def attendanceRecords = new AttendanceRecords()
        attendanceRecords.insert(attendanceRecord1)
        def uncheckedAttendanceSummary = new Fired()

        when:
        def result = uncheckedAttendanceSummary.get(attendanceRecords)

        then:
        result.left().get().isFired() == true

    }
    def "AttendanceSummary-fired-case2" () {
        setup:
        def attendanceRecord1 = FixtureAttendanceRecord.get(2019,5,1,8,59,18,0)
        List<AttendanceRecord> list = List.of(attendanceRecord1)
        def attendanceRecords = new AttendanceRecords(list)
        def uncheckedAttendanceSummary = new Fired()

        when:
        def result = uncheckedAttendanceSummary.get(attendanceRecords)

        then:
        result.right().get().isFired() == false
    }

    def "AttendanceSummary-total-working-hours1" () {
        setup:
        def attendanceRecord1 = FixtureAttendanceRecord.get(2019,5,1,9,0,24,0)
        List<AttendanceRecord> list = List.of(attendanceRecord1)
        def attendanceRecords = new AttendanceRecords(list)
        def uncheckedAttendanceSummary = new Fired()

        when:
        def result =  uncheckedAttendanceSummary.get(attendanceRecords)

        then:
        result.right().get().getRegularTime().toHours() == 8
        result.right().get().getOverTime().toHours() == 4

    }

    def "AttendanceSummary-total-working-hours2" () {
        setup:
        def attendanceRecord1 = FixtureAttendanceRecord.get(2019,5,1,9,0,23,30)
        List<AttendanceRecord> list = List.of(attendanceRecord1)
        def attendanceRecords = new AttendanceRecords(list)
        attendanceRecords.insert(attendanceRecord1)
        def uncheckedAttendanceSummary = new Fired()

        when:
        def result = uncheckedAttendanceSummary.get(attendanceRecords)

        then:
        result.right().get().getRegularTime().toHours() == 8
        result.right().get().getRegularTime().toMinutes() == 8 * 60

        result.right().get().getOverTime().toHours() == 3
        result.right().get().getOverTime().toMinutes() == 3 * 60 + 30
    }

}
