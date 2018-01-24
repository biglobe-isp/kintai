package com.naosim.dddwork.domain.worktotal;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TotalOverWorkMinutes {

    @Getter
    private final Map<String, Integer> totalOverWorkMinutesMap;

    public TotalOverWorkMinutes() {
        totalOverWorkMinutesMap = new HashMap<String, Integer>();
    }
}
