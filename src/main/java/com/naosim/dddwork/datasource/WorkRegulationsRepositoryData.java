package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.WorkRegulationsRepository;
import com.naosim.dddwork.domain.workregulations.AllowanceTimeRange;
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
                .range(AllowanceTimeRange.of(LocalTime.of(6, 0),
                                                      LocalTime.of(9, 0)))
                .build();

        final EndTimeRange endTimeRange = EndTimeRange.builder()
                .standard(LocalTime.of(18, 0))
                // 早退は可能
                .range(AllowanceTimeRange.of(LocalTime.of(9, 0),
                                                    LocalTime.of(18, 0)))
                .build();

        final BreakTimes breakTimes = BreakTimes.builder()
                .lunchBreakTime(TimeRange.of(TimeUnit.of(12, 0),
                                             TimeUnit.of(13, 0)))
                .eveningBreakTime(TimeRange.of(TimeUnit.of(18, 0),
                                               TimeUnit.of(19, 0)))
                .nightBreakTime(TimeRange.of(TimeUnit.of(21, 0),
                                             TimeUnit.of(22, 0)))
                .build();

        return WorkRegulations.builder()
                .startTimeRange(startTimeRange)
                .endTimeRange(endTimeRange)
                .breakTimes(breakTimes)
                .build();
    }
}
