package com.naosim.dddwork.domain.worktotal;


import lombok.Getter;

import java.util.Set;


public class TotalOverWorkTimeYearAndMonth {


    public TotalOverWorkTimeYearAndMonth(TotalOverWorkMinutes totalOverWorkMinutes) {
        value = getTotalOverWorkMinutes(totalOverWorkMinutes);
    }

    @Getter
    private final Integer value;

    private int getTotalOverWorkMinutes(TotalOverWorkMinutes totalOverWorkMinutes) {
        Set<String> keySet = totalOverWorkMinutes.getOverWorkMinutesMap().keySet();
        int totalOverWorkMinutesValue = 0;

        for (String key : keySet) {
            totalOverWorkMinutesValue += totalOverWorkMinutes.getOverWorkMinutesMap().get(key);
        }
        return totalOverWorkMinutesValue;
    }
}
