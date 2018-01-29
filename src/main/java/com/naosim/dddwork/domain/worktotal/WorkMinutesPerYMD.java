package com.naosim.dddwork.domain.worktotal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkMinutesPerYMD {
    @Getter
    private final String targetYearAndMonth;
}
