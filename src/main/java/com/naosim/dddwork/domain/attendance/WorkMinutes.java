package com.naosim.dddwork.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 労働時間
 * 2018/10/01 レビュー指摘事項反映 休憩時間、残業時間の業務仕様を表現。左記により不要メソッドを削除
 * 2018/10/05 レビュー指摘事項反映 Attendanceがファット過ぎるため、WorkMinutesに残業時間関連のメソッドを移動＋ValueObjectを返すように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class WorkMinutes {

    @Getter
    private final int value;

    public OverWorkMinutes calcOverWorkMinutes() {

        // 作業開始から8時間経過以降を残業とする
        if (this.isOverWork()) {
            return new OverWorkMinutes(this.getValue() - 480);
        }
        return new OverWorkMinutes(0);
    }

    private boolean isOverWork() {
        return this.value > 480;
    }
}
