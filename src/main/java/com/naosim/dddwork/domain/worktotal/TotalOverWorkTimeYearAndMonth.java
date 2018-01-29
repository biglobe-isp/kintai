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
        Integer totalOverWorkMinutesValue = totalOverWorkMinutes.getOverWorkMinutesMap().values().stream().mapToInt(x -> x).sum();

        return totalOverWorkMinutesValue;
    }
}
