package com.naosim.dddwork.domain.attendance

import com.naosim.dddwork.domain.attendance.attendancetime.NotVerifiedAttendanceTime
import com.naosim.dddwork.domain.service.OverTimeHoursCalculable
import com.naosim.dddwork.domain.service.WorkingHoursCalculable
import com.naosim.dddwork.domain.TimePoint
import com.naosim.dddwork.domain.service.WorkRegulationsGeneratable
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(locations = ["classpath:context.xml"])
class OverTimeHoursCalculatorSpec extends Specification{

    @Autowired
    private WorkRegulationsGeneratable workRegulationsRepository

    @Autowired
    private WorkingHoursCalculable workingHoursCalculator;

    @Autowired
    private OverTimeHoursCalculable overTimeHoursCalculator;

    @Unroll
    def "残業時間算出"() {
        setup:
        def startTime = StartTime.of(TimePoint.of(startHours, startMinutes))
        def endTime = EndTime.of(TimePoint.of(endHours, endMinutes))
        def notVerifiedAttendanceTime = NotVerifiedAttendanceTime.of(startTime, endTime)
        def attendanceTime = VerifiedAttendanceTime.of(notVerifiedAttendanceTime)
        def workRegulations = workRegulationsRepository.getCurrentRegulations()

        def workingHours = workingHoursCalculator.calcWorkingHours(attendanceTime, workRegulations);
        def overTimeHours = overTimeHoursCalculator.calcOverTimeHours(workingHours, workRegulations);

        expect:
        expectedOverTimeMinutes == overTimeHours.getTimeUnit().getTotalMinutes();

        where:
        startHours | startMinutes | endHours | endMinutes || expectedOverTimeMinutes
        9          | 0            | 18       | 0          || 0
        9          | 0            | 14       | 0          || 0
        10         | 0            | 20       | 0          || 0
        9          | 0            | 20       | 0          || 60
        8          | 0            | 20       | 0          || 120
        9          | 0            | 21       | 0          || 120
        9          | 0            | 23       | 0          || 180
        9          | 0            | 23       | 59         || 239
        9          | 0            | 24       | 00         || 240
        9          | 0            | 25       | 00         || 240
        9          | 0            | 29       | 59         || 240
    }
}
