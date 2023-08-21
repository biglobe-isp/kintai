package kintai.service

import kintai.domain.WorkEnd
import kintai.domain.InputAttendance
import kintai.domain.OverWorkMinutes
import kintai.domain.WorkStart
import kintai.domain.WorkDay
import kintai.domain.WorkMinutes
import kintai.domain.WorkingDateTotalRecord
import spock.lang.Specification

import java.time.LocalDateTime

class InputAttendanceServiceTest extends Specification {
    void setup() {
    }

    def "偽のrepositoryを作成し、勤怠記録へ入れてテストの残業１就業９時間の期待値が帰ってくるかのテスト"() {
        setup:
        def now = LocalDateTime.now()
        def mockRepository = Mock(WorkingDateTotalRecordRepository)
        def sut = new InputAttendanceService(mockRepository)

        def input = new InputAttendance(new WorkDay("2023-07-31"),WorkStart.parse("09:00:00"),new WorkEnd("20:00:00"))
        // 残業１時間　就業9時間の期待値にする。
        def expected = new WorkingDateTotalRecord(new WorkDay("2023-07-31"),WorkStart.parse("09:00:00"),new WorkEnd("20:00:00"),new OverWorkMinutes(60),new WorkMinutes(540))

        when:
        sut.input(input,now)

        then:
        1 * mockRepository.save(expected,now)

    }
}
