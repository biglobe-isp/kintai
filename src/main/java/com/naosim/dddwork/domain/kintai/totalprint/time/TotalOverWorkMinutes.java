package com.naosim.dddwork.domain.kintai.totalprint.time;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TotalOverWorkMinutes {

    @Getter
    private final int value;

}
