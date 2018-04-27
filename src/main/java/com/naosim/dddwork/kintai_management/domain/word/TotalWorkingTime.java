package com.naosim.dddwork.kintai_management.domain.word;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 勤務時間（集計）
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TotalWorkingTime {

    @Getter
    private final Integer value;

}
