package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.utility.TimeUtility;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * 就業時間
 * 2018/10/01 新規作成
 * 　　　 レビュー指摘事項反映 就業時間に開始時刻、終了時刻を含めるように修正
 * 2018/10/05 レビュー指摘事項反映 Attendanceがファット過ぎるため、DutyTimeに労働時間関連のメソッドを移動＋ValueObjectを返すように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class DutyTime {

    @Getter
    private final LocalTime startTime;

    @Getter
    private final LocalTime endTime;

    public WorkMinutes calcWorkTime(WorkDate workDate, RestTimeRule restTimeRule) {

        // 労働時間＝就業時間－休憩時間
        return new WorkMinutes(
                this.calcDutyTime() -
                        this.calcTotalRestTime(workDate, restTimeRule)
        );
    }

    private int calcDutyTime() {

        return (int) this.getStartTime().until(this.getEndTime(), ChronoUnit.MINUTES);
    }

    //@SuppressWarnings("Convert2MethodRef")
    private int calcTotalRestTime(WorkDate workDate, RestTimeRule restTimeRule) {

        return restTimeRule.getValue().stream()
                .filter(restTime ->
                        workDate.getValue().isAfter(restTime.getExpirationStartDate().minusDays(1L)) &&
                                workDate.getValue().isBefore(restTime.getExpirationEndDate().plusDays(1L)))
                .mapToInt(restTime -> this.calcRestTime(restTime))
                //.mapToInt(DutyTime::calcRestTime)
                .sum();
    }

    private int calcRestTime(RestTime restTime) {

        if (this.withinRange(restTime)) {
            return (int) TimeUtility.lateTime(this.getStartTime(), restTime.getStartTime())
                    .until(TimeUtility.earlyTime(this.getEndTime(), restTime.getEndTime()), ChronoUnit.MINUTES);
        }
        return 0;
    }

    private boolean withinRange(RestTime restTime) {

        return !((restTime.getStartTime().isBefore(this.getStartTime()) &&
                restTime.getEndTime().isBefore(this.getStartTime())) ||
                (restTime.getStartTime().isAfter(this.getEndTime()) &&
                        restTime.getEndTime().isAfter(this.getEndTime())));
    }
}
