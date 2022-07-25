package jp.co.biglobe.isp.kintai.datasource;

import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.service.DailyAttendanceRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DailyAttendanceRepositoryCsv implements DailyAttendanceRepository {
    private final Path file;
    private final Clock clock;
    private final DateTimeFormatter attendanceYearMonthFormatter;
    private final DateTimeFormatter attendanceDateFormatter;
    private final DateTimeFormatter attendanceTimeFormatter;

    public DailyAttendanceRepositoryCsv(
            Path file, Clock clock,
            DateTimeFormatter attendanceYearMonthFormatter,
            DateTimeFormatter attendanceDateFormatter,
            DateTimeFormatter attendanceTimeFormatter) {
        this.file = file;
        this.clock = clock;
        this.attendanceYearMonthFormatter = attendanceYearMonthFormatter;
        this.attendanceDateFormatter = attendanceDateFormatter;
        this.attendanceTimeFormatter = attendanceTimeFormatter;
    }

    @Override
    public void persist(DailyAttendance dailyAttendance) throws RuntimeException {
        try (final FileWriter filewriter = new FileWriter(file.toFile(), true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    attendanceDateFormatter.format(dailyAttendance.attendanceDate().value()),
                    attendanceTimeFormatter.format(dailyAttendance.attendanceDuration().attendanceStartTime().value()),
                    attendanceTimeFormatter.format(dailyAttendance.attendanceDuration().attendanceEndTime().value()),
                    dailyAttendance.workTimeMinutes().value(),
                    dailyAttendance.overtimeMinutes().value(),
                    LocalDateTime.now(clock)
            ));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<DailyAttendance> findByAttendanceYearMonth(AttendanceYearMonth attendanceYearMonth) {

        try (final Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
            final Map<String, DailyAttendanceCsv> dailyAttendanceMap = lines
                    .filter(line -> line.startsWith(attendanceYearMonth.format(attendanceYearMonthFormatter)))
                    .map(line -> line.split(","))
                    .map(arr -> new DailyAttendanceCsv(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]))
                    .collect(Collectors.toMap(
                            DailyAttendanceCsv::attendanceDate,
                            Function.identity(),
                            BinaryOperator.maxBy(Comparator.comparing(DailyAttendanceCsv::updatedAt))
                    ));

            return dailyAttendanceMap.values().stream()
                    .map(dailyAttendanceCsv -> dailyAttendanceCsv.toDomain(
                            attendanceDateFormatter,
                            attendanceTimeFormatter
                    )).toList();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
