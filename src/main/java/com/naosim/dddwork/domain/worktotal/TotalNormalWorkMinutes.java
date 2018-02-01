package com.naosim.dddwork.domain.worktotal;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TotalNormalWorkMinutes {

    @Getter
    Map<WorkMinutesPerYMD, Integer> totalWorkMinutesMap;

    public TotalNormalWorkMinutes() {
        totalWorkMinutesMap = new HashMap<WorkMinutesPerYMD, Integer>();
        totalWorkMinutesMap = new HashMap<WorkMinutesPerYMD, Integer>();
    }
}
