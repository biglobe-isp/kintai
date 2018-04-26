package com.naosim.dddwork.kintai_management.domain.word;

import com.naosim.dddwork.kintai_management.domain.system.IsPresentCheckable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 休暇種別
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class HolidayKind implements IsPresentCheckable {
    @Getter
    private final String value;

    public static HolidayKind blank() {
        return new HolidayKind(null);
    }

    public String getFormatValue() {
        if (!isPresent()) {
            return "";
        }
        return value;
    }

}
