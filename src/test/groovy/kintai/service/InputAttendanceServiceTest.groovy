package kintai.service

import kintai.domain.EndWork
import kintai.domain.InputAttendance
import kintai.domain.StartWork
import kintai.domain.WorkDay
import kintai.domain.WorkingDateTotalRecord
import spock.lang.Specification

class InputAttendanceServiceTest extends Specification {
    void setup() {
    }

    def "偽のrepositoryを作成し、勤怠記録へ入れてテストの残業１就業９時間の期待値が帰ってくるかのテスト"() {
        setup:
        def mockRepository = Mock(WorkingDateTotalRecordRepository)
        def sut = new InputAttendance(mockRepository)

        def input = new InputAttendance(new WorkDay("2023-07-31"),new StartWork("09:00:00"),new EndWork("19:00:00"))
        // 残業１時間　就業9時間の期待値にする。
        def expected = new WorkingDateTotalRecord()

        when:
        sut.input(input)

        then:
        1 * mockRepository.save(expected)

    }
}
