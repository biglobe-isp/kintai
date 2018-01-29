package com.naosim.dddwork.domain.worktotal;


import com.naosim.dddwork.domain.workdateandtime.WorkTimeMinutes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


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
