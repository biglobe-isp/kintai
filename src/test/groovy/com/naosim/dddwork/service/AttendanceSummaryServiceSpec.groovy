package com.naosim.dddwork.service

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV
import com.naosim.dddwork.domain.AttendanceRecord
import com.naosim.dddwork.domain.AttendanceRecords
import com.naosim.dddwork.domain.AttendanceSummaryCalculator
import com.naosim.dddwork.domain.date.Month
import com.naosim.dddwork.domain.date.Year
import com.naosim.dddwork.domain.date.YearMonth
import io.vavr.collection.List
import spock.lang.Specification

import static com.naosim.dddwork.domain.FixtureAttendanceRecord.get

class AttendanceSummaryServiceSpec extends Specification {
    def "AttendanceSummary-working-hours-calc1-regular"() {

        setup:
        def attendanceRecord1 = get(2019, 5, 1, 9, 0, 18, 0)
        List<AttendanceRecord> list = List.of(attendanceRecord1)
        def attendanceRecords = new AttendanceRecords(list)
        def attendanceSummaryCalculator = new AttendanceSummaryCalculator()

        when:
        def result = attendanceSummaryCalculator.calculate(attendanceRecords)
        def regularTime = result.get().getRegularWorkingDuration()
        def overTime = result.get().getOverTimeWorkingDuration()

        then:
        regularTime.getRegularWorkingDuration().toHours() == 8
        overTime.getOverTimeWorkingDuration().toHours() == 0
    }

    def "AttendanceRecordSummaryService-TestSimple"() {
        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        attendanceRecordRepositoryCSV.delete()
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()

        when:
        def attendanceRecord1 = get(2019, 5, 1, 9, 0, 18, 0)
        def attendanceRecord2 = get(2019, 5, 2, 9, 0, 18, 0)
        def attendanceRecord3 = get(2019, 5, 3, 9, 0, 20, 0)
        attendanceRecordUpdateService.update(attendanceRecord1)
        attendanceRecordUpdateService.update(attendanceRecord2)
        attendanceRecordUpdateService.update(attendanceRecord3)
        def year = new Year(2019)
        def month = new Month(5)
        def yearMonth = new YearMonth(year, month)
        def attendanceSummaryService = new AttendanceSummaryService()
        def result = attendanceSummaryService.summary(yearMonth)

        then:
        result.get().toString() == "24:0/1:0"
    }

    def "AttendanceRecordSummaryService-TestMinutes"() {
        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        attendanceRecordRepositoryCSV.delete()
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()

        when:
        def attendanceRecord1 = get(2019, 5, 1, 9, 0, 17, 35)
        def attendanceRecord2 = get(2019, 5, 2, 9, 0, 19, 0)
        def attendanceRecord3 = get(2019, 5, 3, 9, 0, 23, 55)
        attendanceRecordUpdateService.update(attendanceRecord1)
        attendanceRecordUpdateService.update(attendanceRecord2)
        attendanceRecordUpdateService.update(attendanceRecord3)
        def year = new Year(2019)
        def month = new Month(5)
        def yearMonth = new YearMonth(year, month)
        def attendanceSummaryService = new AttendanceSummaryService()
        def result = attendanceSummaryService.summary(yearMonth)

        then:
        result.get().toString() == "23:35/3:55"
    }
}
