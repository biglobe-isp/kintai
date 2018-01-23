package com.naosim.dddwork.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkTimeInputParam {
    @Getter
    private final String date;

    @Getter
    private final String start;

    @Getter
    private final String end;

    @Getter
    private final String now;
}
