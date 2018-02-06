package com.naosim.dddwork.domain.worktotal;


import lombok.Getter;


public class TotalNormalWorkTimeYearAndMonth {


    public TotalNormalWorkTimeYearAndMonth(TotalNormalWorkMinutes totalNormalWorkMinutes) {
        value = getTotalNormalWorkMinutes(totalNormalWorkMinutes);
    }

    @Getter
    private final Integer value;

    private int getTotalNormalWorkMinutes(TotalNormalWorkMinutes totalNormalWorkMinutes) {
        int totalWorkMinutes = totalNormalWorkMinutes.getTotalWorkMinutesMap().values().stream().mapToInt(x -> x).sum();

        return totalWorkMinutes;
    }
}
