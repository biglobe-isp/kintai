package com.naosim.dddwork.domain.attendance

import com.naosim.dddwork.domain.TimePoint
import com.naosim.dddwork.domain.WorkRegulationsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime

@ContextConfiguration(locations = ["classpath:context.xml"])
class BreakTimeHoursSpec extends Specification{

    @Autowired
    private WorkRegulationsRepository workRegulationsRepository

    @Unroll
    def "休憩時間算出"() {
        setup:
        def startTime = StartTime.of(TimePoint.of(startHours, startMinutes))
        def endTime = EndTime.of(TimePoint.of(endHours, endMinutes))
        def attendanceTime = AttendanceTime.of(startTime, endTime)
        def workRegulations = workRegulationsRepository.getCurrentRegulations()
        def breakTimeHours = BreakTimeHours.of(attendanceTime, workRegulations)

        expect:
        expectedBreakTimeMinutes == breakTimeHours.getTimeUnit().getTotalMinutes()

        where:
        startHours | startMinutes | endHours | endMinutes || expectedBreakTimeMinutes
        9          | 0            | 18       | 0          || 60
        9          | 0            | 19       | 0          || 120
        12         | 0            | 19       | 0          || 120
        12         | 59           | 19       | 0          || 61
        12         | 0            | 18       | 59         || 119
        13         | 0            | 21       | 0          || 60
        13         | 0            | 22       | 0          || 120
        9          | 0            | 23       | 0          || 180
        9          | 0            | 24       | 0          || 180
    }
}
