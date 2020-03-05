package com.naosim.dddwork.domain.attendance

import com.naosim.dddwork.domain.service.AttendanceGeneratable
import com.naosim.dddwork.domain.TimePoint
import com.naosim.dddwork.domain.service.WorkRegulationsGeneratable
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(locations = ["classpath:context.xml"])
class AttendanceGeneratorSpec extends Specification{

    @Autowired
    private WorkRegulationsGeneratable workRegulationsRepository

    @Autowired
    private AttendanceGeneratable iAttendanceFactory;

    @Unroll
    def "遅刻はAttendance生成しない"() {
        setup:
        def workDay = WorkDay.of("20200301");
        def attendanceTime = VerifiedAttendanceTime.of(StartTime.of(TimePoint.of(hours, minutes)),
                                               EndTime.of(TimePoint.of(18, 0)))
        def workRegulations = workRegulationsRepository.getCurrentRegulations()

        expect:
        expectedValue == iAttendanceFactory.createForRegister(workDay, attendanceTime, workRegulations);

        where:
        hours | minutes || expectedValue
        9     | 1       || null
    }
}
