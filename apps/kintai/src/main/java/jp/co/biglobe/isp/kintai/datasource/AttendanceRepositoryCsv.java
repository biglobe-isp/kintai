package jp.co.biglobe.isp.kintai.datasource;

import jp.co.biglobe.isp.kintai.domain.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.entity.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.entity.MonthlyAttendance;
import jp.co.biglobe.isp.kintai.service.AttendanceRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;

public class AttendanceRepositoryCsv implements AttendanceRepository {
    private final Path file;

    public AttendanceRepositoryCsv(Path file) {
        this.file = file;
    }

    @Override
    public void persist(DailyAttendance dailyAttendance) throws RuntimeException {
        try (FileWriter filewriter = new FileWriter(file.toFile(), true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    dailyAttendance.attendanceDate().toString(),
                    dailyAttendance.startTime().toString(),
                    dailyAttendance.endTime().toString(),
                    dailyAttendance.workTimeMinites(),
                    dailyAttendance.overtimeMinites(),
                    LocalDateTime.now().toString()
            ));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Optional<MonthlyAttendance> findMonthlyAttendance(AttendanceYearMonth attendanceYearMonth) {
        return Optional.empty();
    }
}
