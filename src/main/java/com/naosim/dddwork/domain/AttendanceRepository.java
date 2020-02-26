package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;

import java.io.IOException;
import java.util.List;

public interface AttendanceRepository {
    void save(Attendance attendance) throws IOException;
    List<Attendance> findSpecifiedYearMonth(YearMonth yearMonth) throws Exception;
}
