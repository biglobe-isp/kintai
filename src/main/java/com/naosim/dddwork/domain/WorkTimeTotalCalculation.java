package com.naosim.dddwork.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class WorkTimeTotalCalculation {
    @Getter
    private final Map<String, Integer> totalWorkMinutesMap;

    @Getter
    private final Map<String, Integer> totalOverWorkMinutesMap;
}
