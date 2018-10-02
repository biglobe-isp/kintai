package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 休憩時間ルールFactory
 * 2018/10/01 新規作成
 * 　　　レビュー指摘事項反映 休憩時間、残業時間の業務仕様を表現。休憩時間を範囲として捉えるように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class RestTimeRulesFactory {

    @Getter
    private final List<RestTime> restTimeRules;

    public RestTimeRulesFactory() {
        this.restTimeRules = new ArrayList<>();
    }

    public RestTimeRulesFactory add(LocalTime startTime, LocalTime endTime) {
        this.getRestTimeRules().add(new RestTime(startTime, endTime));
        return this;
    }

    public List<RestTime> build() {
        return this.getRestTimeRules();
    }
}
