package com.naosim.dddwork.domain.kintai.time.work;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WorkYearMonth {

    @Getter
    private final String value;
}
