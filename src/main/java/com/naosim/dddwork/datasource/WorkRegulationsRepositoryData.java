package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.WorkRegulationsRepository;
import com.naosim.dddwork.domain.workregulations.BreakTimes;
import com.naosim.dddwork.domain.workregulations.EndTimeRange;
import com.naosim.dddwork.domain.workregulations.StartTimeRange;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class WorkRegulationsRepositoryData implements WorkRegulationsRepository {

    @Override
    public WorkRegulations getCurrentRegulations() {

        final StartTimeRange startTimeRange = StartTimeRange.builder()
                .standard(LocalTime.of(9, 0))
                // 早出可、遅刻は不可、フレックスなし
                .range(TimeRange.of(TimePoint.of(6, 0),
                                    TimePoint.of(9, 0)))
                .build();

        final EndTimeRange endTimeRange = EndTimeRange.builder()
                .standard(LocalTime.of(18, 0))
                // 早退は可能
                .range(TimeRange.of(TimePoint.of(9, 0),
                                    TimePoint.of(18, 0)))
                .build();

        final BreakTimes breakTimes = BreakTimes.builder()
                .lunchBreakTime(TimeRange.of(TimePoint.of(12, 0),
                                             TimePoint.of(13, 0)))
                .eveningBreakTime(TimeRange.of(TimePoint.of(18, 0),
                                               TimePoint.of(19, 0)))
                .nightBreakTime(TimeRange.of(TimePoint.of(21, 0),
                                             TimePoint.of(22, 0)))
                .build();

        return WorkRegulations.builder()
                .startTimeRange(startTimeRange)
                .endTimeRange(endTimeRange)
                .breakTimes(breakTimes)
                .build();
    }
}
