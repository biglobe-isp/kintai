package com.naosim.dddwork.domain.worktotal;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TotalNormalWorkMinutes {

    @Getter
    Map<String, Integer> totalWorkMinutesMap;

    public TotalNormalWorkMinutes() {
        totalWorkMinutesMap = new HashMap<String, Integer>();
    }


}
