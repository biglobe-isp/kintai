package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class KintaiOfOneDay {

    @Getter
    private final DateString workDate;

    @Getter
    private final TimeString startTime;

    @Getter
    private final TimeString endTime;

    @Getter
    private final Minute workMinutes;

    @Getter
    private final Minute overWorkMinutes;

    @Getter
    private final NowString now;
}
