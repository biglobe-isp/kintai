package com.naosim.dddwork.domain.monthlysummary


import com.naosim.dddwork.domain.AttendanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(locations = ["classpath:context.xml"])
class MonthlySummaryCalculatorSpec extends Specification{

    @Autowired
    private  AttendanceRepository attendanceRepository

    @Autowired
    private MonthlySummaryCalculator monthlySummaryCalculator

    @Unroll
    def "月次集計"() {
        setup:
        YearMonth yearMonth = YearMonth.of(year, month);
        def file = new File("data.csv") << new File("data_total.csv").readBytes()

        MonthlySummary monthlySummary = monthlySummaryCalculator.aggregateSpecifiedMonthAttendance(yearMonth)

        file.delete()

        expect:
        expectedWHour == monthlySummary.getWorkingHours().getHour()
        expectedWMinutes == monthlySummary.getWorkingHours().getMinutes()
        expectedOHour == monthlySummary.getOverTimeHours().getHour()
        expectedOMinutes == monthlySummary.getOverTimeHours().getMinutes()

        where:
        year | month || expectedWHour | expectedWMinutes | expectedOHour | expectedOMinutes
        2020 | 1     || 8             | 0                | 0             | 0
        2020 | 2     || 56            | 15               | 9             | 15
        2020 | 3     || 8             | 0                | 0             | 0
        2020 | 4     || 0             | 0                | 0             | 0
    }
}
