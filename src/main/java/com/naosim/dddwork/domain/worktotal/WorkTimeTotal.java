package com.naosim.dddwork.domain.worktotal;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * 勤務時間合計、残業時間合計
 */
public class WorkTimeTotal {

    private final TotalNormalWorkMinutes totalNormalWorkMinutes;

    private final TotalOverWorkMinutes totalOverWorkMinutes;

    public WorkTimeTotal(TotalNormalWorkMinutes totalNormalWorkMinutes, TotalOverWorkMinutes totalOverWorkMinutes) {
        this.totalNormalWorkMinutes = totalNormalWorkMinutes;
        this.totalOverWorkMinutes = totalOverWorkMinutes;
    }

    public int getTotalNormalWorkMinutes() {
        int totalWorkMinutes = 0;
        Set<String> keySet = totalNormalWorkMinutes.getTotalWorkMinutesMap().keySet();
        for (String key : keySet) {
            totalWorkMinutes += totalNormalWorkMinutes.getTotalWorkMinutesMap().get(key);
        }
        return totalWorkMinutes;
    }

    public int getTotalOverWorkMinutes() {
        Set<String> keySet = totalNormalWorkMinutes.getTotalWorkMinutesMap().keySet();
        int totalOverWorkMinutesValue = 0;

        for (String key : keySet) {
            totalOverWorkMinutesValue += totalOverWorkMinutes.getTotalOverWorkMinutesMap().get(key);
        }
        return totalOverWorkMinutesValue;
    }


}
