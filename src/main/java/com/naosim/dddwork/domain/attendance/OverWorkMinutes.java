package com.naosim.dddwork.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 残業時間
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class OverWorkMinutes {

    @Getter
    private final int value;

    public OverWorkMinutes(WorkMinutes workMinutes) {

        this.value = this.calcOverWorkMinutes(workMinutes);
    }

    private int calcOverWorkMinutes(WorkMinutes workMinutes) {

        return Math.max(workMinutes.getValue() - 8 * 60, 0);
    }
}
