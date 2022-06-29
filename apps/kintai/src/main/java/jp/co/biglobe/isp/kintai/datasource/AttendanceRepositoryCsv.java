package jp.co.biglobe.isp.kintai.datasource;

import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.daily.OvertimeMinutes;
import jp.co.biglobe.isp.kintai.domain.daily.WorkTimeMinutes;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
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
        try (FileWriter filewriter = new FileWriter(file.toFile(), true)) {
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
    public List<DailyAttendance> findMonthlyAttendance(AttendanceYearMonth attendanceYearMonth) {

        final Map<String, Optional<DailyAttendanceCsv>> dailyAttendanceCsvMap;
        try (Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8);) {
            dailyAttendanceCsvMap = lines.filter(
                            line -> line.startsWith(attendanceYearMonth.value().format(attendanceYearMonthFormatter)))
                    .map(line -> line.split(","))
                    .map(arr -> new DailyAttendanceCsv(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]))
                    .collect(Collectors.groupingBy(
                            DailyAttendanceCsv::attendanceDate,
                            Collectors.maxBy(Comparator.comparing(DailyAttendanceCsv::updatedAt))
                    ));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        final List<DailyAttendance> result =
                dailyAttendanceCsvMap.values().stream()
                        .map(Optional::get)
                        .map(dailyCsv ->
                                     new DailyAttendance(
                                             new AttendanceDate(LocalDate.parse(
                                                     dailyCsv.attendanceDate(),
                                                     attendanceDateFormatter
                                             )),
                                             new AttendanceStartTime(LocalTime.parse(
                                                     dailyCsv.attendanceStartTime(),
                                                     attendanceTimeFormatter
                                             )),
                                             new AttendanceEndTime(
                                                     LocalTime.parse(
                                                             dailyCsv.attendanceEndTime(),
                                                             attendanceTimeFormatter
                                                     )),
                                             new WorkTimeMinutes(
                                                     Integer.valueOf(dailyCsv.workTimeMinutes())
                                             ),
                                             new OvertimeMinutes(Integer.valueOf(dailyCsv.overTimeMinutes()))
                                     )
                        )
                        .collect(Collectors.toUnmodifiableList());

        return result;
    }
}
