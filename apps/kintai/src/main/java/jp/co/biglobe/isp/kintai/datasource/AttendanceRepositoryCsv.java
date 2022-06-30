package jp.co.biglobe.isp.kintai.datasource;

import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.daily.OvertimeMinutes;
import jp.co.biglobe.isp.kintai.domain.daily.WorkTimeMinutes;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.DailyAttendancesOfMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.MonthlyAttendance;
import jp.co.biglobe.isp.kintai.service.AttendanceRepository;
import jp.co.biglobe.isp.kintai.service.DailyAttendanceFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AttendanceRepositoryCsv implements AttendanceRepository {
    private final Path file;
    private final Clock clock;
    private final DateTimeFormatter attendanceYearMonthFormatter;
    private final DateTimeFormatter attendanceDateFormatter;
    private final DateTimeFormatter attendanceTimeFormatter;

    public AttendanceRepositoryCsv(
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
                    attendanceTimeFormatter.format(dailyAttendance.startTime().value()),
                    attendanceTimeFormatter.format(dailyAttendance.endTime().value()),
                    dailyAttendance.workTimeMinutes().value(),
                    dailyAttendance.overtimeMinutes().value(),
                    LocalDateTime.now(clock)
            ));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public MonthlyAttendance findMonthlyAttendance(AttendanceYearMonth attendanceYearMonth) {

        final List<DailyAttendance> dailyAttendanceList;
        try (final Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8);) {
            final Map<String, Optional<DailyAttendanceCsv>> dailyAttendanceMap = lines
                    .filter(line -> line.startsWith(attendanceYearMonth.value().format(attendanceYearMonthFormatter)))
                    .map(line -> line.split(","))
                    .map(arr -> new DailyAttendanceCsv(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]))
                    .collect(Collectors.groupingBy(
                            DailyAttendanceCsv::attendanceDate,
                            Collectors.maxBy(Comparator.comparing(DailyAttendanceCsv::updatedAt))
                    ));
            dailyAttendanceList = dailyAttendanceMap.values().stream()
                    .map(Optional::get)
                    .map(dailyAttendanceCsv -> dailyAttendanceCsv.toDomain(attendanceDateFormatter,
                                                                           attendanceTimeFormatter))
                    .collect(Collectors.toUnmodifiableList());            ;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        final DailyAttendancesOfMonth dailyAttendancesOfMonth = new DailyAttendancesOfMonth(dailyAttendanceList);

        final MonthlyAttendance monthlyAttendance = new MonthlyAttendance(attendanceYearMonth, dailyAttendancesOfMonth);

        return monthlyAttendance;
    }
}
