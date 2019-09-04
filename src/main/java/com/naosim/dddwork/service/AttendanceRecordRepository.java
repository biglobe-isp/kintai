package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.date.YearMonth;

public interface AttendanceRecordRepository {
    AttendanceRecords load();
    AttendanceRecords load(YearMonth yearMonth);
    void save(AttendanceRecords attendanceRecords);
}
