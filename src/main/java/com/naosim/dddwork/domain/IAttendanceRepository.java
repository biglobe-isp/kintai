package com.naosim.dddwork.domain;

import java.util.List;

public interface IAttendanceRepository {
    Attendance save(Attendance attendance);
    List<Attendance> searchByYearMonth(String yearMonth);
}
