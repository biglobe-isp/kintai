package com.naosim.dddwork.kintai.service.timerecord

import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.naosim.dddwork.kintai.domain.actualtime.WrappedActualMinutes
import com.naosim.dddwork.kintai.domain.attendance.FixtureAttendanceDate
import com.naosim.dddwork.kintai.domain.attendance.WrappedAttendanceTimeInterval
import com.naosim.dddwork.kintai.domain.regulation.FixtureRegulatedBreakTimeShift
import com.naosim.dddwork.kintai.domain.regulation.FixtureRegulatedWorkingTimeMinutes
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecordDomainService
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes
import com.naosim.dddwork.kintai.service.AttendanceRepository
import com.naosim.dddwork.kintai.service.RegulationRepository
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class AttendanceRecordingServiceSpec extends Specification {
    private AttendanceRepository attendanceRepository = Mock()
    private RegulationRepository regulationRepository = Mock()
    private AttendanceRecordDomainService attendanceRecordDomainService = Mock()
    @Subject
    private AttendanceRecordingService attendanceRecordingService

    def "打刻のテスト #label "() {
        setup:
        def expectedDate = FixtureAttendanceDate.get()
        def expectedInterval = WrappedAttendanceTimeInterval.get(startTime, endTime)
        def expectedRegulatedBreakTimeShift = FixtureRegulatedBreakTimeShift.get()
        def expectedRegulatedWorkingTimeMinutes = FixtureRegulatedWorkingTimeMinutes.get()
        def expectedAttendanceRecord = new AttendanceRecord(FixtureAttendanceDate.get(), WrappedAttendanceTimeInterval.get(startTime, endTime), WrappedActualMinutes.get(workingMinutes))

        when:
        def result = attendanceRecordingService.record(expectedDate, expectedInterval)

        then:
        1 * regulationRepository.fetchBreakTimeShift(_) >> { AttendanceDate actualDate -> // MEMO: メソッドの引数がここにくる
            //MEMO: inの検証　lambdaの中にいるときはassert必須
            assert actualDate == expectedDate
            // MEMO: outの検証 return値
            expectedRegulatedBreakTimeShift
        }

        1 * regulationRepository.fetchRegulatedWorkingTimeMinutes(_) >> { AttendanceDate actualDate ->
            assert actualDate == expectedDate
            expectedRegulatedWorkingTimeMinutes
        }

        1 * attendanceRecordDomainService.createAttendanceRecord(*_) >> {
            AttendanceDate actualAttendanceDate,
            AttendanceTimeInterval actualAttendanceTimeInterval,
            RegulatedBreakTimeShift actualRegulatedBreakTimeShift,
            RegulatedWorkingTimeMinutes actualRegulatedWorkingTimeMinutes ->
                assert actualAttendanceDate == expectedDate
                assert actualAttendanceTimeInterval == expectedInterval
                assert actualRegulatedBreakTimeShift == expectedRegulatedBreakTimeShift
                assert actualRegulatedWorkingTimeMinutes == expectedRegulatedWorkingTimeMinutes
                expectedAttendanceRecord
        }

        1 * attendanceRepository.register(*_) >> {AttendanceRecord actualAttendanceRecord ->
            assert actualAttendanceRecord == expectedAttendanceRecord
        }
        assert result == expectedAttendanceRecord

        where:
        label | startTime | endTime | workingMinutes | overtimeMinutes
        "not working" | "0900" | "0900" | 0 | 0
        "no overtime(1 break time)" | "0900" | "1800" | 480 | 0
        "no overtime(2 break times)" | "0900" | "1900" | 480 | 0
        "leave early" | "0900" | "1315" | 240 | 0
        "overtime(work early)" | "0800" | "1800" | 540 | 60
        "overtime(work lately & 2 break times)" | "0900" | "1915" | 495 | 15
        "overtime(work lately & 3 break times)" | "0900" | "2135" | 600 | 120
    }
}
