package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

//TODO 不要
/**
 * 出勤時刻（分）
 * 2018/10/01 レビュー指摘事項反映 就業時間に開始時刻、終了時刻を含めるように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class StartTimeMinutes {
    @Getter
    private final int value;
}
