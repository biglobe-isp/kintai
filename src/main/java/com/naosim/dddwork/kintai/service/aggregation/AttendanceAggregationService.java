package com.naosim.dddwork.kintai.service.aggregation;

import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceAggregationMonthly;
import com.naosim.dddwork.kintai.service.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttendanceAggregationService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceAggregationMonthly aggregateMonthly(AggregationMonth aggregationMonth) {
        return attendanceRepository.fetchMonthly(aggregationMonth);
    }
}
