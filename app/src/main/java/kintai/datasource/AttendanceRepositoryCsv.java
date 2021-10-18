package kintai.datasource;

import kintai.domain.Attendance;
import kintai.domain.AttendanceRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * 勤怠CSVリポジトリ.
 */
public class AttendanceRepositoryCsv implements AttendanceRepository {

    private final AttendanceMapperCsv attendanceMapperCsv;

    public AttendanceRepositoryCsv(AttendanceMapperCsv attendanceMapperCsv) {
        this.attendanceMapperCsv = attendanceMapperCsv;
    }

    @Override
    public void persist(Attendance attendance) {
        attendanceMapperCsv.save(attendance);
    }


    @Override
    public List<Attendance> select(LocalDate yearMonth) {
        return attendanceMapperCsv.findByYearMonth(yearMonth);
    }
}
