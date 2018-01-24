package com.naosim.dddwork.domain.workdateandtime;

import lombok.Getter;

public class OverWorkTimeMinutes {
    @Getter
    private Integer value;

    public OverWorkTimeMinutes(int normalWorkTime) {
        value = normalWorkTime;
    }
}
