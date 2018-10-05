package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 休憩時間
 * 2018/10/01 新規作成
 * 　　　レビュー指摘事項反映 休憩時間、残業時間の業務仕様を表現
 * 2018/10/05 レビュー指摘事項反映 RestTimeを有効期限付きとして、休憩時間変更があってもコード上のロジックに影響がないように修正
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class RestTime {

    @Getter
    private final LocalTime startTime;

    @Getter
    private final LocalTime endTime;

    @Getter
    private final LocalDate expirationStartDate;

    @Getter
    private final LocalDate expirationEndDate;
}
