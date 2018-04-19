package com.naosim.dddwork.domain.word;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 勤務時間（分）
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkingMinute {

    /**
     * 入力値
     */
    @Getter
    private final BigDecimal value;

    /**
     * コンストラクタ
     */
    public WorkingMinute(BigDecimal value) {
        this.value = value;
    }
}
