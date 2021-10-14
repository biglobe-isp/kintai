package kintai.datasource;

import kintai.domain.Attendance;
import kintai.domain.AttendanceRepository;

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

    }

    @Override
    public List<Attendance> select(int yearMonth) {
        attendanceMapperCsv.get();

        return null;
    }
}
