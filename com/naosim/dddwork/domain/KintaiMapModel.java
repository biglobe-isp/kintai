package com.naosim.dddwork.domain;

import java.util.Map;

public class KintaiMapModel {
    private Map<String, Integer> totalWorkMinutesMap;
    private Map<String, Integer> totalOverWorkMinutes;

    public KintaiMapModel(Map<String, Integer> totalWorkMinutesMap, Map<String, Integer> totalOverWorkMinutes) {
        this.totalWorkMinutesMap = totalWorkMinutesMap;
        this.totalOverWorkMinutes = totalOverWorkMinutes;
    }

    public Map<String, Integer> getTotalWorkMinutesMap() {
        return totalWorkMinutesMap;
    }

    public Map<String, Integer> getTotalOverWorkMinutesMap() {
        return totalOverWorkMinutes;
    }
}
