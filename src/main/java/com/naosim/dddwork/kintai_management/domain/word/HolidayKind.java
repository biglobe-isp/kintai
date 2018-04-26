package com.naosim.dddwork.kintai_management.domain.word;

import com.naosim.dddwork.kintai_management.domain.system.IsPresentCheckable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 休暇種別
 */
@AllArgsConstructor
public class HolidayKind implements IsPresentCheckable {

    @Getter
    private final String value;

    public String getFormatValue() {
        if (!isPresent()) {
            return "";
        }
        return value;
    }

}
