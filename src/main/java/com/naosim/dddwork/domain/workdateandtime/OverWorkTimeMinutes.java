package com.naosim.dddwork.domain.workdateandtime;

import lombok.Getter;

public class OverWorkTimeMinutes {
    @Getter
    private Integer value;

    private final AllWorkTimeMinutes allWorkTimeMinutes;

    public OverWorkTimeMinutes(AllWorkTimeMinutes allWorkTimeMinutes) {
        this.allWorkTimeMinutes = allWorkTimeMinutes;
        value = calcAllWorkTimeMinutes();
    }

    /**
     * 残業時間の計算
     *
     * @return 残業時間
     */
    private int calcAllWorkTimeMinutes() {
        return Math.max(this.allWorkTimeMinutes.getValue() - 8 * 60, 0);
    }
}
