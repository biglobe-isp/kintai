package kintai.datasource;

import kintai.domain.Attendance;
import kintai.domain.AttendanceRepository;

import java.time.YearMonth;
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
        attendanceMapperCsv.save("data.csv", attendance);
    }


    @Override
    public List<Attendance> select(YearMonth yearMonth) {
        return attendanceMapperCsv.findByYearMonth("data.csv", yearMonth);
    }
}
