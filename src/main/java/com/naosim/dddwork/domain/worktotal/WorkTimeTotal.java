package com.naosim.dddwork.domain.worktotal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class WorkTimeTotal {
    @Getter
    private final TotalNormalWorkMinutes totalNormalWorkMinutes;

    @Getter
    private final TotalOverWorkMinutes totalOverWorkMinutes;
}
