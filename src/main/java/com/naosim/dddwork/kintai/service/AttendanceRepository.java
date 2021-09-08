package com.naosim.dddwork.kintai.service;

import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecords;

public interface AttendanceRepository {
    void register(AttendanceRecord attendanceRecord) throws Exception;
    AttendanceRecords fetchMonthly(AggregationMonth aggregationMonth) throws Exception;
}
