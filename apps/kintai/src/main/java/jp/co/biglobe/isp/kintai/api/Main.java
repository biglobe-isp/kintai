package jp.co.biglobe.isp.kintai.api;

import jp.co.biglobe.isp.kintai.datasource.AttendanceRepositoryCsv;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.service.AttendanceManagementService;
import jp.co.biglobe.isp.kintai.service.AttendanceRepository;
import jp.co.biglobe.isp.kintai.service.DailyAttendanceFactory;

import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final Clock clock = Clock.systemDefaultZone();
    private static final Path csvFilePath = Path.of("./output/data.csv");
    private static final AttendanceRepository attendanceRepository = new AttendanceRepositoryCsv(csvFilePath, clock);
    private static final DailyAttendanceFactory dailyAttendanceFactory = new DailyAttendanceFactory();
    private static final AttendanceManagementService service = new AttendanceManagementService(
            attendanceRepository,
            dailyAttendanceFactory
    );
    private static final DateTimeFormatter FORMATTER_ATTENDANCE_DATE = DateTimeFormatter.BASIC_ISO_DATE;
    private static final DateTimeFormatter FORMATTER_ATTENDANCE_TIME = DateTimeFormatter.ofPattern("HHmm");

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が不足しています。");
            }
            switch (MethodType.of(args[0])) {
                case INPUT -> input(args);
                case TOTAL -> System.out.println("集計機能は未実装です。");
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

        service.inputAttendance(
                new AttendanceDate(LocalDate.parse(args[1], FORMATTER_ATTENDANCE_DATE)),
                new AttendanceStartTime(LocalTime.parse(args[2], FORMATTER_ATTENDANCE_TIME)),
                new AttendanceEndTime(LocalTime.parse(args[3], FORMATTER_ATTENDANCE_TIME))
        );
    }
}
