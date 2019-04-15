package com.naosim.dddwork.datasource;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimePointPair;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.WorkRegulation;
import com.naosim.dddwork.domain.WorkRegulationRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public class WorkRegulationRepositoryDefault implements WorkRegulationRepository {

    @Override
    public WorkRegulation fetchDefault() {

        TimePoint standardStartTime = createTimePoint(9, 0);   //  9時開始  // いまのところ不使用
        TimePoint standardEndTime = createTimePoint(18, 0);    // 18時終了  // 〃
        TimePoint minStartTime = createTimePoint(0, 0);        // 早出はあり
        TimePoint minEndTime = createTimePoint(0, 0);          // 早退はあり（コアタイムなし）
        TimePoint maxStartTime = createTimePoint(9, 0);        // 遅刻はなし
        TimePoint maxEndTime = createTimePoint(23, 59);         // 日付を越えたらサービス残業
        ImmutableList<TimePointPair> restTimes =
                ImmutableList.of(
                        createTimePointPair(12, 13),
                        createTimePointPair(18, 19),
                        createTimePointPair(21, 22)
                );
        WorkMinute standardWorkMinute = createWorkMinute(8 * 60);

        return new WorkRegulation(
                standardStartTime,
                minStartTime,
                maxStartTime,
                standardEndTime,
                minEndTime,
                maxEndTime,
                restTimes,
                standardWorkMinute
        );
    }

    private TimePoint createTimePoint(int hour, int minute) {
        return new TimePoint(LocalTime.of(hour, minute));
    }

    private TimePointPair createTimePointPair(int startHour, int endHour) {
        return new TimePointPair(
                new TimePoint(LocalTime.of(startHour, 0)),
                new TimePoint(LocalTime.of(endHour, 0))
        );
    }

    private WorkMinute createWorkMinute(int minute) {
        return new WorkMinute(minute);
    }
}
