package com.naosim.dddwork.domain.word;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 勤務時間（時）
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkingHour {

    /**
     * 入力値
     */
    @Getter
    private final BigDecimal value;

    /**
     * コンストラクタ
     */
    public WorkingHour(BigDecimal value) {
        this.value = value;
    }
}
