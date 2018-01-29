package com.naosim.dddwork.domain.worktotal;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TotalOverWorkMinutes {

    @Getter
    Map<String, Integer> overWorkMinutesMap;

    @Getter
    Map<WorkMinutesPerYMD, Integer> testOMap;

    public TotalOverWorkMinutes() {
        overWorkMinutesMap = new HashMap<String, Integer>();
        testOMap = new HashMap<WorkMinutesPerYMD, Integer>();
    }
}
