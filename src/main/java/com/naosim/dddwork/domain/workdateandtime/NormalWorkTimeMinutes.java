package com.naosim.dddwork.domain.workdateandtime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NormalWorkTimeMinutes {
    @Getter
    private Integer value;

    public NormalWorkTimeMinutes(int normalWorkTime) {
        value = normalWorkTime;
    }
}
