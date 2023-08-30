package kintai.service

import kintai.domain.InputAttendance.WorkEnd
import kintai.domain.InputAttendance.InputAttendance
import kintai.domain.WorkingDateTotalRecord.OverWorkMinutes
import kintai.domain.InputAttendance.WorkStart
import kintai.domain.InputAttendance.WorkDay
import kintai.domain.WorkingDateTotalRecord.WorkMinutes
import kintai.domain.WorkingDateTotalRecord.WorkingDateTotalRecord
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

        def input = new InputAttendance(WorkDay.parse("2023-07-31"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"))
        // 残業１時間　就業9時間の期待値にする。//540
        def expected = new WorkingDateTotalRecord(WorkDay.parse("2023-07-31"),WorkStart.parse("09:00:00"),WorkEnd.parse("20:00:00"),new WorkMinutes(540),new OverWorkMinutes(60))

        when:
        sut.input(input,now)

        then:
        1 * mockRepository.save(expected,now)

    }
}
