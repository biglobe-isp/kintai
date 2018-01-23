package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class OneDayKintai {

    @Getter
    private final String workDate;

    @Getter
    private final String startTime;

    @Getter
    private final String endTime;

    @Getter
    private final String workMinutes;

    @Getter
    private final String overWorkMinutes;

    @Getter
    private final String now;
}
