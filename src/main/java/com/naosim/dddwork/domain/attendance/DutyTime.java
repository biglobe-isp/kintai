package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

/**
 * 就業時間
 * 2018/10/01 新規作成
 * 　　　 レビュー指摘事項反映 就業時間に開始時刻、終了時刻を含めるように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class DutyTime {

    @Getter
    private final LocalTime startTime;

    @Getter
    private final LocalTime endTime;

    boolean withinRange(RestTime restTime) {

        return !((restTime.getStartTime().isBefore(this.getStartTime()) &&
                restTime.getEndTime().isBefore(this.getStartTime())) ||
                (restTime.getStartTime().isAfter(this.getEndTime()) &&
                        restTime.getEndTime().isAfter(this.getEndTime())));
    }
}
