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

        // 20181002 仕様変更 ADD START
        // 仕様変更 来月の途中から休憩時間が増える
        // 来月15日から休憩が増えます。時間は15:00-16:00。
        // 2018/11/15以降では、休憩時間ルールに15:00-16:00を追加する。
        RestTimeRulesFactory restTimeRulesFactory = new RestTimeRulesFactory();

        if (workDate.is20181115Later()) {
            restTimeRulesFactory
                    .add(LocalTime.of(12, 0, 0), LocalTime.of(13, 0, 0))
                    .add(LocalTime.of(18, 0, 0), LocalTime.of(19, 0, 0))
                    .add(LocalTime.of(21, 0, 0), LocalTime.of(22, 0, 0))
                    .add(LocalTime.of(15, 0, 0), LocalTime.of(16, 0, 0));
        } else {
            restTimeRulesFactory
                    .add(LocalTime.of(12, 0, 0), LocalTime.of(13, 0, 0))
                    .add(LocalTime.of(18, 0, 0), LocalTime.of(19, 0, 0))
                    .add(LocalTime.of(21, 0, 0), LocalTime.of(22, 0, 0));
        }
        // 20181002 仕様変更 ADD END

        WorkMinutes workMinutes = new WorkMinutes(
                calcWorkTime(
                        dutyTime,
                        // 20181002 仕様変更 MOD START
//                        new RestTimeRulesFactory()
//                                .add(LocalTime.of(12, 0, 0), LocalTime.of(13, 0, 0))
//                                .add(LocalTime.of(18, 0, 0), LocalTime.of(19, 0, 0))
//                                .add(LocalTime.of(21, 0, 0), LocalTime.of(22, 0, 0))
//                                .build()
                        restTimeRulesFactory.build()
                        // 20181002 仕様変更 MOD END
                )
        );

        return new Attendance(
                workDate,
                dutyTime,
                workMinutes,
                new OverWorkMinutes(
                        calcOverWorkMinutes(workMinutes)
                )
        );
    }

    private static int calcWorkTime(DutyTime dutyTime, List<RestTime> restTimeList) {

        // 労働時間＝就業時間－休憩時間
        return calcDutyTime(dutyTime) - calcTotalRestTime(dutyTime, restTimeList);
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

    private static int calcOverWorkMinutes(WorkMinutes workMinutes) {

        // 作業開始から8時間経過以降を残業とする
        if (workMinutes.isOverWork()) {
            return workMinutes.getValue() - 480;
        }
        return 0;
    }
}
