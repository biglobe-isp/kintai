package com.naosim.dddwork.domain.kintai;

import com.naosim.dddwork.domain.kintai.time.work.WorkYearMonth;
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
