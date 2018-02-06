package com.naosim.dddwork.domain.worktotal;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TotalOverWorkMinutes {


    @Getter
    Map<WorkMinutesPerYMD, Integer> overWorkMinutesMap;

    public TotalOverWorkMinutes() {
        overWorkMinutesMap = new HashMap<WorkMinutesPerYMD, Integer>();
    }
}
