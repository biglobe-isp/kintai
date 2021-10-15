package kintai.datasource;

import kintai.domain.Attendance;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceMapperCsv {
    void save(Attendance attendance);

    void update(Attendance attendance);

    void findByYearMonth(int yearMonth);

    Optional<Attendance> findByDay(LocalDate day);
}
