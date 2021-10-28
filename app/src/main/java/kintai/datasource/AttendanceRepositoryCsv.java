package kintai.datasource;

import kintai.domain.Attendance;
import kintai.domain.AttendanceRepository;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;

/**
 * 勤怠CSVリポジトリ.
 */
public class AttendanceRepositoryCsv implements AttendanceRepository {

    private final AttendanceMapperCsv attendanceMapperCsv;

    private final Path path;

    public AttendanceRepositoryCsv(AttendanceMapperCsv attendanceMapperCsv, Path path) {
        this.attendanceMapperCsv = attendanceMapperCsv;
        this.path = path;
    }

    @Override
    public void persist(Attendance attendance) {
        attendanceMapperCsv.save(path, attendance);
    }


    @Override
    public List<Attendance> select(YearMonth yearMonth) {
        return attendanceMapperCsv.findByYearMonth(path, yearMonth);
    }
}
