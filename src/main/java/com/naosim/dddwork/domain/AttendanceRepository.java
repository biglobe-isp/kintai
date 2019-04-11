package com.naosim.dddwork.domain;

import java.util.List;

public interface AttendanceRepository {

    void save(Attendance attendance);

    List<Attendance> fetchMonthly(YearMonth yearMonth);
}
