package com.naosim.dddwork.kintai_management.domain.word;

import com.naosim.dddwork.kintai_management.domain.system.IsPresentCheckable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 勤務時間
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class WorkingTime {

    @Getter
    private final Integer value;

}
