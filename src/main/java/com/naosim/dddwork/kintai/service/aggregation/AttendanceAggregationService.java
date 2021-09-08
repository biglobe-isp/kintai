package com.naosim.dddwork.kintai.service.aggregation;

import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceMonthlySummary;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecords;
import com.naosim.dddwork.kintai.service.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttendanceAggregationService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceMonthlySummary aggregateMonthly(AggregationMonth aggregationMonth) throws Exception {
        AttendanceRecords attendanceRecords = attendanceRepository.fetchMonthly(aggregationMonth);

        return new AttendanceMonthlySummary(attendanceRecords);
    }
}
