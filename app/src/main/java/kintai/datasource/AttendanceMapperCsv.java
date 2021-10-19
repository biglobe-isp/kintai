package kintai.datasource;

import kintai.domain.Attendance;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface AttendanceMapperCsv {
    void save(String fileName,Attendance attendance);

    List<Attendance> findByYearMonth(String fileName, YearMonth yearMonth);
}
