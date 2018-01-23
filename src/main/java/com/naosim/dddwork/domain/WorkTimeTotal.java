package com.naosim.dddwork.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * 勤務時間合計、残業時間合計
 */
public class WorkTimeTotal {

    private final Map<String, Integer> totalWorkMinutesMap;

    private final Map<String, Integer> totalOverWorkMinutesMap;

    private int totalWorkMinutes;

    private int totalOverWorkMinutes;

    public WorkTimeTotal(Map<String, Integer> totalWorkMinutesMap, Map<String, Integer> totalOverWorkMinutesMap) {
        this.totalWorkMinutesMap = totalWorkMinutesMap;
        this.totalOverWorkMinutesMap = totalOverWorkMinutesMap;
    }

    public int getTotalWorkMinutes() {
        Set<String> keySet = totalWorkMinutesMap.keySet();
        for (String key : keySet) {
            totalWorkMinutes += totalWorkMinutesMap.get(key);
        }
        return totalWorkMinutes;
    }

    public int getTotalOverWorkMinutes() {
        Set<String> keySet = totalWorkMinutesMap.keySet();
        for (String key : keySet) {
            totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }
        return totalOverWorkMinutes;
    }


}
