package com.naosim.dddwork.kintai_management.domain.word;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 勤務開始時刻
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class WorkingStartTime {
    @Getter
    private final String value;

    public static WorkingStartTime blank() {
        return new WorkingStartTime(null);
    }
}
