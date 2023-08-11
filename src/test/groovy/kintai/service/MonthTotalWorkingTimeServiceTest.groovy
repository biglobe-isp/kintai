package kintai.service

import kintai.domain.FixtureWorkingDateTotalRecord
import kintai.domain.MonthTotalOverWorkMinutes
import kintai.domain.MonthTotalWorkMinutes
import kintai.domain.MonthTotalWorkingTime

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

        def fixture = FixtureWorkingDateTotalRecord.get()
        mockRepository.findByMonth(input) >> fixture
        when :
        def actual = sut.total(input)

        then:
        actual == expected
    }
}
