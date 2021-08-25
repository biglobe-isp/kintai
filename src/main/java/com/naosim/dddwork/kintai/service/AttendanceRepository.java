package com.naosim.dddwork.kintai.service;

import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.aggregation.AttendanceAggregationMonthly;
import com.naosim.dddwork.kintai.domain.timerecord.AttendanceRecord;

public interface AttendanceRepository {
    public void register(AttendanceRecord attendanceRecord);
    public AttendanceAggregationMonthly fetchMonthly(AggregationMonth aggregationMonth);
}
