package com.naosim.dddwork.datasource;

import java.util.List;

import com.naosim.dddwork.domain.Attendance;

public interface IAttendanceRecordRepository {
    void save(Attendance attendance);
    List<Attendance> searchByYearMonth(String yearMonth);
}
