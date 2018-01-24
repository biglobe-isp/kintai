package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WorkStartAndEndTimeOfOneDay {

    @Getter
    private final DateString workDateString;

    @Getter
    private final TimeString startTimeString;

    @Getter
    private final TimeString endTimeString;

    @Getter
    private final NowString nowString;
}
