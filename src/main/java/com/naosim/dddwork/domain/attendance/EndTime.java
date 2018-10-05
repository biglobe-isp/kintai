package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

/**
 * 退勤時刻
 * 2018/10/01 レビュー指摘事項反映 就業時間に開始時刻、終了時刻を含めるように修正。左記により不要メソッドを削除
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class EndTime {

    @Getter
    private final LocalTime value;
}
