package kintai.service

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

        def fixture = List.of(
                (new WorkingDateTotalRecord(new WorkDay("2023-08-01"),new WorkStart("9:00:00"),new WorkEnd("20:00:00"),new OverWorkMinutes(60),new WorkMinutes(540))),
                (new WorkingDateTotalRecord(new WorkDay("2023-08-02"),new WorkStart("9:00:00"),new WorkEnd("20:30:00"),new OverWorkMinutes(90),new WorkMinutes(570))),
                (new WorkingDateTotalRecord(new WorkDay("2023-08-03"),new WorkStart("9:00:00"),new WorkEnd("20:40:00"),new OverWorkMinutes(100),new WorkMinutes(580)))
        )
        mockRepository.findByMonth(input) >> fixture
        when :
        def actual = sut.total(input)

        then:
        actual == expected
    }
}
