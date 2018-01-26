package com.naosim.dddwork.domain.workdateandtime;

import lombok.Getter;

public class OverWorkTimeMinutes {
    @Getter
    private Integer value;

    private final AllWorkTimeMinutes allWorkTimeMinutes;

    public OverWorkTimeMinutes(AllWorkTimeMinutes allWorkTimeMinutes) {
        this.allWorkTimeMinutes = allWorkTimeMinutes;
        value = Math.max(this.allWorkTimeMinutes.getValue() - 8 * 60, 0);
    }
}
