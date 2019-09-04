package com.naosim.dddwork.service

import com.naosim.dddwork.datasource.AttendanceRecordRepositoryCSV
import com.naosim.dddwork.domain.AttendanceRecord
import com.naosim.dddwork.domain.FixtureAttendanceRecord
import com.naosim.dddwork.domain.date.Month
import com.naosim.dddwork.domain.date.Year
import com.naosim.dddwork.domain.date.YearMonth
import spock.lang.Specification

import static com.naosim.dddwork.domain.FixtureAttendanceRecord.get

class AttendanceRecordUpdateServiceSpec extends Specification {
    def "AttendanceRecordUpdateService-Normal"() {
        setup:
        def attendanceRecord = get(2019, 4, 1, 9, 0, 18, 0)

        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()
        attendanceRecordUpdateService.update(attendanceRecord)

        when:
        def test = false
        def repository = new AttendanceRecordRepositoryCSV();
        YearMonth key = new YearMonth(new Year(2019), new Month(4))
        def result = repository.load(key)
        def attendanceRecords = result.getAttendanceRecords()
        for (AttendanceRecord attendanceRecord1 : attendanceRecords) {
            if (attendanceRecord1.getWorkingDate().getValue() == 20190401) {
                if (attendanceRecord1.getWorkingDuration().getStartTime().getValue() == 900 &&
                        attendanceRecord1.getWorkingDuration().getEndTime().getValue() == 1800)
                    test = true;
            }
        }

        then:
        test == true
    }

    def "AttendanceRecordUpdateService-Overtime1"() {
        setup:
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()

        attendanceRecordUpdateService.update(FixtureAttendanceRecord.get(2019, 4, 1, 9, 0, 25, 30))

        when:
        def repository = new AttendanceRecordRepositoryCSV();
        YearMonth key = new YearMonth(new Year(2019), new Month(4))
        def attendanceRecords = repository.load(key)
        def workingDuration = attendanceRecords.getAttendanceRecords().get(0).getWorkingDuration()

        then:
        workingDuration.toString() == "0900-2400"
    }

    def "AttendanceRecordUpdateService-Overtime2"() {
        setup:
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()
        attendanceRecordUpdateService.update(FixtureAttendanceRecord.get(2019, 4, 1, 9, 0, 2, 0))

        when:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        YearMonth key = new YearMonth(new Year(2019), new Month(4))
        def attendanceRecords = attendanceRecordRepositoryCSV.load(key)
        def workingDuration = attendanceRecords.getAttendanceRecords().get(0).getWorkingDuration()

        then:
        workingDuration.toString() == "0900-2400"
    }

    def "AttendanceRecordUpdateService-MultiEntry"() {
        setup:
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()

        attendanceRecordUpdateService.update(FixtureAttendanceRecord.get(2019, 5, 1, 9, 0, 18, 0))
        attendanceRecordUpdateService.update(FixtureAttendanceRecord.get(2019, 5, 2, 9, 0, 25, 0))
        attendanceRecordUpdateService.update(FixtureAttendanceRecord.get(2019, 5, 3, 9, 0, 2, 0))

        when:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        YearMonth key = new YearMonth(new Year(2019), new Month(5))
        def attendanceRecords = attendanceRecordRepositoryCSV.load(key)
        def workingDuration1 = attendanceRecords.getAttendanceRecords().get(0).getWorkingDuration()
        def workingDuration2 = attendanceRecords.getAttendanceRecords().get(1).getWorkingDuration()
        def workingDuration3 = attendanceRecords.getAttendanceRecords().get(2).getWorkingDuration()

        then:
        workingDuration1.toString() == "0900-1800"
        workingDuration2.toString() == "0900-2400"
        workingDuration3.toString() == "0900-2400"
    }
}
