package kintai.service

import kintai.domain.FixtureWorkingDateTotalRecordList
import kintai.domain.MonthTotalOverWorkMinutes
import kintai.domain.MonthTotalWorkMinutes
import kintai.domain.MonthTotalWorkingTime
import kintai.domain.OverWorkMinutes
import kintai.domain.WorkDay
import kintai.domain.WorkEnd
import kintai.domain.WorkMinutes
import kintai.domain.WorkStart
import kintai.domain.WorkingDateTotalRecord
import spock.lang.Specification

import java.time.YearMonth

class MonthTotalWorkingTimeServiceTest extends Specification {
    void setup(){
    }

    def "集計月を渡したらその月の集計が返ってくる。"(){
        setup:
        def mockRepository = Mock(WorkingDateTotalRecordRepository)

        def sut = new MonthTotalWorkingTimeService(mockRepository)
        def input = YearMonth.of(2023, 8)
        def expected = new MonthTotalWorkingTime(new MonthTotalWorkMinutes(1690),new MonthTotalOverWorkMinutes(250))

        def fixture = FixtureWorkingDateTotalRecordList.get()
        mockRepository.findByMonth(input) >> fixture
        when :
        def actual = sut.total(input)

        then:
        actual == expected
    }
    def "一か月分以上の月を渡した場合"(){
        setup:
        def mockRepository = Mock(WorkingDateTotalRecordRepository)

        def sut = new MonthTotalWorkingTimeService(mockRepository)
        def input = YearMonth.of(2023, 8)

        mockRepository.findByMonth(input) >>
                List.of(
                        (new WorkingDateTotalRecord(WorkDay.parse("2023-08-01"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new WorkMinutes(540),new OverWorkMinutes(60))),
                        (new WorkingDateTotalRecord(WorkDay.parse("2023-08-02"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:30:00"),new WorkMinutes(570),new OverWorkMinutes(90))),
                        (new WorkingDateTotalRecord(WorkDay.parse("2023-10-03"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:40:00"),new WorkMinutes(580),new OverWorkMinutes(100)))
                )
        when:
        sut.total(input)

        then:
        thrown(IllegalArgumentException)
    }
}
