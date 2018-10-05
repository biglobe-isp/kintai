package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalTime;

/**
 * 就業時間
 * 2018/10/01 新規作成
 * 　　　 レビュー指摘事項反映 就業時間に開始時刻、終了時刻を含めるように修正
 * 2018/10/05 レビュー指摘事項反映 Attendanceがファット過ぎるため、DutyTimeに労働時間関連のメソッドを移動＋ValueObjectを返すように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class DutyTime extends TimeSpan {

    public DutyTime(LocalTime startTime, LocalTime endTime) {
        super(startTime, endTime);
    }

    public WorkMinutes calcWorkMinutes(WorkDate workDate, RestTimeRule restTimeRule) {

        // 労働時間＝就業時間－総休憩時間
        return new WorkMinutes(
                this.calcSpanTime() -
                        this.calcTotalRestMinutes(workDate, restTimeRule)
        );
    }

    private int calcTotalRestMinutes(WorkDate workDate, RestTimeRule restTimeRule) {

        return restTimeRule.getValue().stream()
                .filter(restTime ->
                        workDate.getValue().isAfter(restTime.getExpirationStartDate().minusDays(1L)) &&
                                workDate.getValue().isBefore(restTime.getExpirationEndDate().plusDays(1L)))
                .mapToInt(this::calcOverlapMinutes)
                .sum();
    }
}
