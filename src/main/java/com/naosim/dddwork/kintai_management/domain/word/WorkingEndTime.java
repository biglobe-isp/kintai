package com.naosim.dddwork.kintai_management.domain.word;

import com.naosim.dddwork.kintai_management.domain.system.IsPresentCheckable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 勤務終了時刻
 */
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
