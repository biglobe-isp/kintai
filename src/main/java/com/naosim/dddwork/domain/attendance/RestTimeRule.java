package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 休憩時間規則
 * 2018/10/03 新規作成
 * 　　　仕様変更 来月の途中から休憩時間が増える
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class RestTimeRule {
    @Getter
    private final List<RestTime> value;
}
