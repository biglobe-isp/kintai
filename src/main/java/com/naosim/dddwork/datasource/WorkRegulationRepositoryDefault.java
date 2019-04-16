package com.naosim.dddwork.datasource;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.WorkRegulation;
import com.naosim.dddwork.domain.WorkRegulationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class WorkRegulationRepositoryDefault implements WorkRegulationRepository {

    @Override
    public WorkRegulation fetchDefault() {
        return WorkRegulation.builder()
                .standardStartTime(TimePoint.of(9, 0))   //  9時開始  // いまのところ不使用
                .standardEndTime(TimePoint.of(18, 0))    // 18時終了  // 〃
                .minStartTime(TimePoint.of(0, 0))        // 早出はあり
                .minEndTime(TimePoint.of(0, 0))          // 早退はあり（コアタイムなし）
                .maxStartTime(TimePoint.of(9, 0))        // 遅刻はなし
                .maxEndTime(TimePoint.of(24, 0))         // 日付を越えたらサービス残業
                .restTimes(ImmutableList.of(
                        TimeRange.of(12, 0, 13, 0),
                        TimeRange.of(18, 0, 19, 0),
                        TimeRange.of(21, 0, 22, 0)
                ))
                .standardWorkMinute(WorkMinute.of(8 * 60))              // 基準労働時間は8時間
                .build();
    }
}
