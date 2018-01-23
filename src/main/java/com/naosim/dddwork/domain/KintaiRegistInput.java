package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class KintaiRegistInput {

    @Getter
    private final String date;

    @Getter
    private final String startTime;

    @Getter
    private final String endTime;

    @Getter
    private final String now;
}
