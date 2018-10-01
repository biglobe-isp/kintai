package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

//TODO 不要
/**
 * 退勤時刻（時）
 * 2018/10/01 レビュー指摘事項反映 就業時間に開始時刻、終了時刻を含めるように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class EndTimeHours {
    @Getter
    private final int value;

    public boolean isOver12Hours() {
        return this.getValue() > 12;
    }

    public boolean isOver18Hours() {
        return this.getValue() > 18;
    }

    public boolean isOver21Hours() {
        return this.getValue() > 21;
    }

    public boolean isBreak() {
        return this.getValue() == 12 || this.getValue() == 18 || this.getValue() == 21;
    }
}
