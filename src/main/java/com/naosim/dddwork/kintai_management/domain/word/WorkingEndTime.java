package com.naosim.dddwork.kintai_management.domain.word;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 勤務終了時刻
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class WorkingEndTime {
    @Getter
    private final String value;

    public static WorkingEndTime blank() {
        return new WorkingEndTime(null);
    }
}
