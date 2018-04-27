package com.naosim.dddwork.kintai_management.domain.word;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 集計年月
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TotalYearMonth {

    @Getter
    private final String value;

}
