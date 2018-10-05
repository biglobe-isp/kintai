package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 休憩時間規則ファクトリ
 * 2018/10/01 新規作成
 * 　　　レビュー指摘事項反映 休憩時間、残業時間の業務仕様を表現。休憩時間を範囲として捉えるように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class RestTimeRuleFactory {

    @Getter
    private final List<RestTime> restTimeList;

    public RestTimeRuleFactory() {
        this.restTimeList = new ArrayList<>();
    }

    public RestTimeRuleFactory add(LocalTime startTime,
                                   LocalTime endTime,
                                   LocalDate expirationStartDate,
                                   LocalDate expirationEndDate) {

        this.getRestTimeList().add(new RestTime(startTime, endTime, expirationStartDate, expirationEndDate));

        return this;
    }

    public RestTimeRule build() {
        return new RestTimeRule(this.getRestTimeList());
    }
}
