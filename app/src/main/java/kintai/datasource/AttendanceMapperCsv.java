package kintai.datasource;

import kintai.domain.Attendance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceMapperCsv {
    void save(Attendance attendance);

    List<Attendance> findByYearMonth(LocalDate yearMonth);

}
