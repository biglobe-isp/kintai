package com.naosim.dddwork.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 残業時間
 * 2018/10/01 レビュー指摘事項反映 就業時間に開始時刻、終了時刻を含めるように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class OverWorkMinutes {

    @Getter
    private final int value;
    // 不要メソッドを削除
//
//    public OverWorkMinutes(WorkMinutes workMinutes) {
//
//        this.value = this.calcOverWorkMinutes(workMinutes);
//    }
//
//    private int calcOverWorkMinutes(WorkMinutes workMinutes) {
//
//        return Math.max(workMinutes.getValue() - 8 * 60, 0);
//    }
}
