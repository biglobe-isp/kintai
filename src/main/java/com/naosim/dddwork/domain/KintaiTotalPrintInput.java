package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.time.work.WorkYearMonth;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
// TODO: クラス名を業務に即したものに修正
public class KintaiTotalPrintInput {

    @Getter
    private final WorkYearMonth workYearMonth;
}
