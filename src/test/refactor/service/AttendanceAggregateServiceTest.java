package refactor.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import refactor.datasource.CsvFileRepository;
import refactor.domain.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttendanceAggregateServiceTest {
    private List<String> inputs = Arrays.asList(
            "20190701 0900 1800",
            "20190702 0900 1200",
            "20190703 0900 1210",
            "20190704 0900 1830",
            "20190705 0900 1900",
            "20190708 0900 2059",
            "20190709 0900 2100",
            "20190710 0900 2159",
            "20190711 0900 2200",
            "20190712 0900 2250",
            "20190716 0900 2300",
            "20190717 0900 1800",
            "20190718 0900 1900",
            "20190719 0900 1800",
            "20190701 0900 1700",
            "20190722 0900 1800",
            "20190723 0900 1800",
            "20190724 0900 1800",
            "20190725 0900 1800",
            "20190726 0900 1800",
            "20190729 0900 1800",
            "20190730 0900 1800",
            "20190731 0900 1800",
            "20190801 0900 1700",
            "20190802 0900 2000"
    );

    private static final String FILE_NAME = "data.csv";

    @Before
    public void setUp() throws Exception {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }

        for (String input : inputs) {
            String[] params = input.split(" ");

            // 勤務日の生成
            WorkingDay workingDay = new WorkingDay(params[0]);

            // 勤務開始時刻の生成
            StartTime startTime = new StartTime(params[1]);

            // 勤務終了時刻の生成
            EndTime endTime = new EndTime(params[2]);

            // 勤怠入力時刻の生成
            AttendanceInputTime attendanceInputTime = new AttendanceInputTime();

            // 日次勤怠記録の生成
            DailyAttendanceRecord dailyAttendanceRecord = new DailyAttendanceRecord(
                    workingDay, startTime, endTime, attendanceInputTime);

            // 勤怠リポジトリの生成
            AttendanceRepository repository = new CsvFileRepository();

            // 勤怠サービスの生成
            AttendanceInputService attendanceInputService = new AttendanceInputService(
                    dailyAttendanceRecord, repository);

            attendanceInputService.inputAttendance();
        }
    }

    @After
    public void tearDown() throws Exception {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void calculateTotalWorkingHours() {
        AttendanceRepository attendanceRepository = new CsvFileRepository();
        AttendanceAggregateService attendanceAggregateService = new AttendanceAggregateService(attendanceRepository);
        YearMonth yearMonth = new YearMonth("201907");

        TotalWorkingHours totalWorkingHours = attendanceAggregateService.calculateTotalWorkingHours(yearMonth);

        Assert.assertEquals("178時間49分", totalWorkingHours.toString());
    }

    @Test
    public void calculateTotalOvertimeHours() {
        AttendanceRepository attendanceRepository = new CsvFileRepository();
        AttendanceAggregateService attendanceAggregateService = new AttendanceAggregateService(attendanceRepository);
        YearMonth yearMonth = new YearMonth("201907");

        TotalOvertimeHours totalOvertimeHours = attendanceAggregateService.calculateTotalOvertimeHours(yearMonth);

        Assert.assertEquals("13時間49分", totalOvertimeHours.toString());
    }
}