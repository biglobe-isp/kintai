package com.naosim.dddwork.domain.worktotal;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;


public class TotalNormalWorkTimeYearAndMonth {


    public TotalNormalWorkTimeYearAndMonth(TotalNormalWorkMinutes totalNormalWorkMinutes) {
        value = getTotalNormalWorkMinutes(totalNormalWorkMinutes);
    }

    @Getter
    private final Integer value;

    private int getTotalNormalWorkMinutes(TotalNormalWorkMinutes totalNormalWorkMinutes) {
        int totalWorkMinutes = 0;
        Set<String> keySet = totalNormalWorkMinutes.getTotalWorkMinutesMap().keySet();
        for (String key : keySet) {
            totalWorkMinutes += totalNormalWorkMinutes.getTotalWorkMinutesMap().get(key);
        }
        return totalWorkMinutes;
    }
}
