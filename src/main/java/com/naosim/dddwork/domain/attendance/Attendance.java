package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 勤怠
 * 2018/09/28 レビュー指摘事項反映 休憩時間、残業時間の業務仕様を表現。休憩時間を範囲として捉えるように修正
 * 2018/10/05 レビュー指摘事項反映 Attendanceがファット過ぎるため、DutyTimeに労働時間関連のメソッドを移動＋ValueObjectを返すように修正
 * 2018/10/05 レビュー指摘事項反映 Attendanceがファット過ぎるため、WorkMinutesに残業時間関連のメソッドを移動＋ValueObjectを返すように修正
 * 2018/10/05 レビュー指摘事項反映 RestTimeを有効期限付きとして、休憩時間変更があってもコード上のロジックに影響がないように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class Attendance {

    @Getter
    private final WorkDate workDate;

    @Getter
    private final DutyTime dutyTime;

    @Getter
    private final WorkMinutes workMinutes;

    @Getter
    private final OverWorkMinutes overWorkMinutes;

    private Attendance(WorkDate workDate, StartTime startTime, EndTime endTime) {

        this.workDate = workDate;

        this.dutyTime = new DutyTime(startTime.getValue(), endTime.getValue());

        this.workMinutes = this.getDutyTime().calcWorkMinutes(this.getWorkDate(), this.makeRestTimeRule());

        this.overWorkMinutes = this.getWorkMinutes().calcOverWorkMinutes();
    }

    public static Attendance create(WorkDate workDate, StartTime startTime, EndTime endTime) {

        return new Attendance(workDate, startTime, endTime);
    }

    private RestTimeRule makeRestTimeRule() {

        // 仕様変更 来月の途中から休憩時間が増える
        // 来月15日から休憩が増えます。時間は15:00-16:00。
        return new RestTimeRuleFactory()
                .add(LocalTime.of(12, 0, 0), LocalTime.of(13, 0, 0),
                        LocalDate.of(1900, 1, 1), LocalDate.of(9999, 12, 31))
                .add(LocalTime.of(15, 0, 0), LocalTime.of(16, 0, 0),
                        LocalDate.of(2018, 11, 15), LocalDate.of(9999, 12, 31))
                .add(LocalTime.of(18, 0, 0), LocalTime.of(19, 0, 0),
                        LocalDate.of(1900, 1, 1), LocalDate.of(9999, 12, 31))
                .add(LocalTime.of(21, 0, 0), LocalTime.of(22, 0, 0),
                        LocalDate.of(1900, 1, 1), LocalDate.of(9999, 12, 31))
                .build();
    }
}
