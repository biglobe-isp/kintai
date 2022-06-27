package jp.co.biglobe.isp.kintai.datasource;

import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.monthly.MonthlyAttendance;
import jp.co.biglobe.isp.kintai.service.AttendanceRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

public class AttendanceRepositoryCsv implements AttendanceRepository {
    private final Path file;
    private final Clock clock;
    public AttendanceRepositoryCsv(Path file, Clock clock) {
        this.file = file;
        this.clock = clock;
    }

    @Override
    public void persist(DailyAttendance dailyAttendance) throws RuntimeException {
        try (FileWriter filewriter = new FileWriter(file.toFile(), true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    dailyAttendance.attendanceDate().value().toString(),
                    dailyAttendance.startTime().value().toString(),
                    dailyAttendance.endTime().value().toString(),
                    dailyAttendance.workTimeMinites().value(),
                    dailyAttendance.overtimeMinites().value(),
                    LocalDateTime.now(clock).toString()
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
