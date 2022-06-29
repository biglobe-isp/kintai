package jp.co.biglobe.isp.kintai.api;

import jp.co.biglobe.isp.kintai.datasource.AttendanceRepositoryCsv;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.TotalWorkedHoursResult;
import jp.co.biglobe.isp.kintai.service.AttendanceManagementService;
import jp.co.biglobe.isp.kintai.service.AttendanceRepository;
import jp.co.biglobe.isp.kintai.service.DailyAttendanceFactory;

import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final Clock clock = Clock.systemDefaultZone();
    private static final Path csvFilePath = Path.of("./output/data.csv");
    private static final DateTimeFormatter FORMATTER_ATTENDANCE_YEARMONTH = DateTimeFormatter.ofPattern("yyyyMM");
    private static final DateTimeFormatter FORMATTER_ATTENDANCE_DATE = DateTimeFormatter.BASIC_ISO_DATE;
    private static final DateTimeFormatter FORMATTER_ATTENDANCE_TIME = DateTimeFormatter.ofPattern("HHmm");
    private static final AttendanceRepository attendanceRepository = new AttendanceRepositoryCsv(
            csvFilePath,
            clock,
            FORMATTER_ATTENDANCE_YEARMONTH,
            FORMATTER_ATTENDANCE_DATE,
            FORMATTER_ATTENDANCE_TIME
    );
    private static final DailyAttendanceFactory dailyAttendanceFactory = new DailyAttendanceFactory();
    private static final AttendanceManagementService service = new AttendanceManagementService(
            attendanceRepository,
            dailyAttendanceFactory
    );

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が不足しています。");
            }
            switch (MethodType.of(args[0])) {
                case INPUT -> input(args);
                case TOTAL -> total();
                default -> throw new RuntimeException("引数のメソッドタイプは定義されていません。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void input(String[] args) {
        if (args.length < 4) {
            throw new RuntimeException("引数が不足しています。");
        }

        final InputArgs inputArgs = new InputArgs(args[1], args[2], args[3]);

        service.inputAttendance(
                new AttendanceDate(LocalDate.parse(inputArgs.attendanceDate(), FORMATTER_ATTENDANCE_DATE)),
                new AttendanceStartTime(LocalTime.parse(inputArgs.attendanceStartTime(), FORMATTER_ATTENDANCE_TIME)),
                new AttendanceEndTime(LocalTime.parse(inputArgs.attendanceEndTime(), FORMATTER_ATTENDANCE_TIME))
        );
    }

    private static void total() {

        final AttendanceYearMonth attendanceYearMonth = new AttendanceYearMonth(YearMonth.now(clock));

        TotalWorkedHoursResult totalWorkedHoursResult = service.totalWorkingHours(attendanceYearMonth);

    }
}
