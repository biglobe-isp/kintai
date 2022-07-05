package jp.co.biglobe.isp.kintai.api;

import jp.co.biglobe.isp.kintai.datasource.DailyAttendanceRepositoryCsv;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDuration;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.TotalWorkedHoursResult;
import jp.co.biglobe.isp.kintai.service.AttendanceManagementService;
import jp.co.biglobe.isp.kintai.service.DailyAttendanceRepository;
import jp.co.biglobe.isp.kintai.service.DailyAttendanceFactory;

import java.nio.file.Path;
import java.text.MessageFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Main {
    private static final Clock clock = Clock.systemDefaultZone();
    private static final Path csvFilePath = Path.of("./output/data.csv");
    private static final DateTimeFormatter FORMATTER_ATTENDANCE_YEARMONTH = DateTimeFormatter.ofPattern("yyyyMM");
    private static final DateTimeFormatter FORMATTER_ATTENDANCE_DATE = DateTimeFormatter.BASIC_ISO_DATE;
    private static final DateTimeFormatter FORMATTER_ATTENDANCE_TIME = DateTimeFormatter.ofPattern("HH_mm");
    private static final DailyAttendanceRepository attendanceRepository = new DailyAttendanceRepositoryCsv(
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
    private static final DateTimeFormatter FORMATTER_STDOUT_YEARMONTH = DateTimeFormatter.ofPattern("yyyy年MM月");

    public static void main(String[] args) {
        try {
            validateArgsLength(args, 1);
            switch (MethodType.of(args[0])) {
                case INPUT -> input(Arrays.copyOfRange(args, 1, args.length));
                case TOTAL -> total();
                default -> throw new RuntimeException("引数のメソッドタイプは定義されていません。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgsLength(String[] args, int x) {
        if (args.length < x) {
            throw new RuntimeException("引数が不足しています。");
        }
    }

    private static void input(String[] args) {
        validateArgsLength(args, 3);
        validateInputArgsPrefix(args);

        final InputArgs inputArgs = new InputArgs(
                args[0].replace(InputArgsPrefix.DATE.getValue(), ""),
                args[1].replace(InputArgsPrefix.START.getValue(), ""),
                args[2].replace(InputArgsPrefix.END.getValue(), "")
        );

        service.inputAttendance(
                new AttendanceDate(LocalDate.parse(inputArgs.attendanceDate(), FORMATTER_ATTENDANCE_DATE)),
                new AttendanceDuration(
                        new AttendanceStartTime(LocalTime.parse(
                                inputArgs.attendanceStartTime(),
                                FORMATTER_ATTENDANCE_TIME
                        )),
                        new AttendanceEndTime(LocalTime.parse(inputArgs.attendanceEndTime(), FORMATTER_ATTENDANCE_TIME))
                )
        );
    }

    private static void validateInputArgsPrefix(String[] args) {
        if (!InputArgsPrefix.DATE.containsPrefix(args[0])) {
            throw new RuntimeException("勤怠日付を入力してください。");
        }
        if (!InputArgsPrefix.START.containsPrefix(args[1])) {
            throw new RuntimeException("勤怠開始時刻を入力してください。");
        }
        if (!InputArgsPrefix.END.containsPrefix(args[2])) {
            throw new RuntimeException("勤怠終了時刻を入力してください。");
        }
    }

    private static void total() {

        final AttendanceYearMonth attendanceYearMonth = new AttendanceYearMonth(YearMonth.now(clock));

        final TotalWorkedHoursResult totalWorkedHoursResult = service.totalWorkingHours(attendanceYearMonth);

        System.out.println(MessageFormat.format(
                """
                        {0}の勤怠集計結果：
                        勤務時間合計：{1}分
                        残業時間合計：{2}分
                        """,
                FORMATTER_STDOUT_YEARMONTH.format(attendanceYearMonth.value()),
                String.valueOf(totalWorkedHoursResult.totalWorkTimeMinutes().value()),
                String.valueOf(totalWorkedHoursResult.totalOvertimeMinutes().value())
        ));
    }
}
