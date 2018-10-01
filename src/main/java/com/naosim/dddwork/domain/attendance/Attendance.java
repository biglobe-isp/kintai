package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 勤怠
 * 2018/09/28 レビュー指摘事項反映 休憩時間、残業時間の業務仕様を表現。休憩時間を範囲として捉えるように修正
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

    public static Attendance create(WorkDate workDate, StartTime startTime, EndTime endTime) {

        DutyTime dutyTime = new DutyTime(startTime.getValue(), endTime.getValue());

        return new Attendance(
                workDate,
                dutyTime,
                calcWorkTime(
                        dutyTime,
                        new RestTimeRulesFactory()
                                .add(LocalTime.of(12, 0, 0), LocalTime.of(13, 0, 0))
                                .add(LocalTime.of(18, 0, 0), LocalTime.of(19, 0, 0))
                                .add(LocalTime.of(21, 0, 0), LocalTime.of(22, 0, 0))
                                .create()
                ),
                calcOverWorkMinutes(
                        dutyTime,
                        new StipulatedTime(LocalTime.of(18, 0, 0))
                )
        );
    }

    private static WorkMinutes calcWorkTime(DutyTime dutyTime, List<RestTime> restTimeList) {

        // 労働時間＝就業時間－休憩時間
        return new WorkMinutes(
                calcDutyTime(dutyTime) - calcTotalRestTime(dutyTime, restTimeList)
        );
    }

    private static int calcDutyTime(DutyTime dutyTime) {

        return (int) dutyTime.getStartTime().until(dutyTime.getEndTime(), ChronoUnit.MINUTES);
    }

    private static int calcTotalRestTime(DutyTime dutyTime, List<RestTime> restTimeList) {

        return restTimeList.stream()
                .mapToInt(restTime -> calcRestTime(dutyTime, restTime))
                .sum();
    }

    private static int calcRestTime(DutyTime dutyTime, RestTime restTime) {

        if (dutyTime.withinRange(restTime)) {
            return (int) lateTime(dutyTime.getStartTime(), restTime.getStartTime()).until(
                    earlyTime(dutyTime.getEndTime(), restTime.getEndTime()), ChronoUnit.MINUTES);
        }
        return 0;
    }

    private static LocalTime earlyTime(LocalTime one, LocalTime other) {
        if (one.isBefore(other)) {
            return one;
        }
        return other;
    }

    private static LocalTime lateTime(LocalTime one, LocalTime other) {
        if (one.isAfter(other)) {
            return one;
        }
        return other;
    }

    private static OverWorkMinutes calcOverWorkMinutes(DutyTime dutyTime, StipulatedTime stipulatedTime) {

        // 残業時間＝退勤時間ー定時
        if (dutyTime.getEndTime().isAfter(stipulatedTime.getValue())) {
            return new OverWorkMinutes(
                    (int) stipulatedTime.getValue().until(dutyTime.getEndTime(), ChronoUnit.MINUTES)
            );
        }
        return new OverWorkMinutes(0);
    }
}
