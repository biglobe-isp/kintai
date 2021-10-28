package kintai.datasource;

import kintai.domain.Attendance;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;

public interface AttendanceMapperCsv {
    void save(Path path, Attendance attendance);

    List<Attendance> findByYearMonth(Path path, YearMonth yearMonth);
}
