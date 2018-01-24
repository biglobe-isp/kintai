package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.time.work.WorkYearMonth;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class KintaiTotalPrintTargetYearMonth {

    @Getter
    private final WorkYearMonth workYearMonth;
}
