package com.naosim.dddwork.kintai_management.domain.word;

import com.naosim.dddwork.kintai_management.domain.system.IsPresentCheckable;
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
public class WorkingEndTime implements IsPresentCheckable {

    @Getter
    private final String value;

    public String getFormatValue() {
        if (!isPresent()) {
            return "";
        }
        return value;
    }
}
