package com.naosim.dddwork.domain.attendance

import com.naosim.dddwork.api.attendancetime.NotVerifiedAttendanceTime
import com.naosim.dddwork.domain.service.WorkingHoursCalculable
import com.naosim.dddwork.domain.TimePoint
import com.naosim.dddwork.domain.service.WorkRegulationsGeneratable
import com.naosim.dddwork.api.attendancetime.VerifiedAttendanceTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(locations = ["classpath:context.xml"])
class WorkingHoursCalculatorSpec extends Specification{

    @Autowired
    private WorkRegulationsGeneratable workRegulationsRepository

    @Autowired
    private WorkingHoursCalculable workingHoursCalculator;

    @Unroll
    def "勤務時間算出"() {
        setup:
        def startTime = StartTime.of(TimePoint.of(startHours, startMinutes))
        def endTime = EndTime.of(TimePoint.of(endHours, endMinutes))
        def notVerifiedAttendanceTime = NotVerifiedAttendanceTime.of(startTime, endTime)
        def verifiedAttendanceTime = VerifiedAttendanceTime.of(notVerifiedAttendanceTime)
        def attendanceTime = AttendanceTime.of(verifiedAttendanceTime)

        def workRegulations = workRegulationsRepository.getCurrentRegulations()
        def workingHours = workingHoursCalculator.calcWorkingHours(attendanceTime, workRegulations);

        expect:
        expectedWorkingTimeMinutes == workingHours.getTimeUnit().getTotalMinutes()

        where:
        startHours | startMinutes | endHours | endMinutes || expectedWorkingTimeMinutes
        9          | 0            | 18       | 0          || 480
        9          | 0            | 19       | 0          || 480
        8          | 0            | 19       | 0          || 540
        6          | 0            | 19       | 0          || 660
        9          | 0            | 21       | 0          || 600
        9          | 0            | 23       | 0          || 660
        9          | 0            | 24       | 0          || 720
        9          | 0            | 25       | 0          || 720
    }
}
