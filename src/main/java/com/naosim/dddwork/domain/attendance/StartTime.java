package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

/**
 * 出勤時刻
 * 2018/10/01 レビュー指摘事項反映 就業時間に開始時刻、終了時刻を含めるように修正。左記により不要メソッドを削除
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class StartTime {

    @Getter
    private final LocalTime value;
    // 不要メソッドを削除
//    public StartTimeHours makeStartTimeHours() {
//        return new StartTimeHours(this.getValue().getHour());
//    }
//
//    public StartTimeMinutes makeStartTimeMinutes() {
//        return new StartTimeMinutes(this.getValue().getMinute());
//    }
}
