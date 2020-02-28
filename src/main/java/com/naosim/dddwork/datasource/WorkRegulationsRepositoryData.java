package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.WorkRegulationsRepository;
import com.naosim.dddwork.domain.workregulations.BreakTimes;
import com.naosim.dddwork.domain.workregulations.EndTimeRange;
import com.naosim.dddwork.domain.workregulations.StartTimeRange;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
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

        List<TimeRange> breakTimesList = new ArrayList<>();
        TimeRange lunchBreakTime = TimeRange.of(TimePoint.of(12, 0),
                                                TimePoint.of(13, 0));
        breakTimesList.add(lunchBreakTime);
        TimeRange eveningBreakTime = TimeRange.of(TimePoint.of(18, 0),
                                                  TimePoint.of(19, 0));
        breakTimesList.add(eveningBreakTime);
        TimeRange nightBreakTime = TimeRange.of(TimePoint.of(21, 0),
                                                TimePoint.of(22, 0));
        breakTimesList.add(nightBreakTime);

        final BreakTimes breakTimes = BreakTimes.builder()
                .list(breakTimesList)
                .build();

        return WorkRegulations.builder()
                .startTimeRange(startTimeRange)
                .endTimeRange(endTimeRange)
                .breakTimes(breakTimes)
                .build();
    }
}
