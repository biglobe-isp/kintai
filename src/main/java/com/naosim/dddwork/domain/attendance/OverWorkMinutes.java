package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 残業時間
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class OverWorkMinutes {
    @Getter
    private final Long value;
}
