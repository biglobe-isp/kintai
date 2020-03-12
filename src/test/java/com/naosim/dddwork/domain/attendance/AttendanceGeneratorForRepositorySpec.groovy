package com.naosim.dddwork.domain.attendance


import com.naosim.dddwork.api.attendancetime.VerifiedAttendanceTime
import com.naosim.dddwork.api.attendancetime.NotVerifiedAttendanceTime
import com.naosim.dddwork.domain.service.AttendanceGeneratableForRegister
import com.naosim.dddwork.domain.TimePoint
import com.naosim.dddwork.domain.service.WorkRegulationsGeneratable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(locations = ["classpath:context.xml"])
class AttendanceGeneratorForRepositorySpec extends Specification{

    @Autowired
    private WorkRegulationsGeneratable workRegulationsRepository

    @Autowired
    private AttendanceGeneratableForRegister attendanceGenerator;

    @Unroll
    def "遅刻はAttendance生成しない"() {
        setup:
        def workDay = WorkDay.of("20200301");
        def notVerifiedAttendanceTime = NotVerifiedAttendanceTime.of(
                StartTime.of(TimePoint.of(9, 1)),
                EndTime.of(TimePoint.of(18, 0)))
        def verifiedAttendanceTime = VerifiedAttendanceTime.of(notVerifiedAttendanceTime)
        def attendanceTime = AttendanceTime.of(verifiedAttendanceTime)
        def workRegulations = workRegulationsRepository.getCurrentRegulations()

        when:
        attendanceGenerator.create(workDay, attendanceTime, workRegulations);

        then:
        thrown(RuntimeException)
    }
}
