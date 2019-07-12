package refactor.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import refactor.datasource.CsvFileRepository;
import refactor.domain.*;

public class AttendanceInputServiceTest {
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
            "20190717 0800 1800",
            "20190718 0800 1900",
            "20190719 0900 1800",
            "20190722 0900 1800",
            "20190723 0900 1800",
            "20190724 0900 1800",
            "20190725 0900 1800",
            "20190726 0900 1800",
            "20190729 0900 1800",
            "20190730 0900 1800",
            "20190731 0900 1800"
    );

    private List<String> expected = Arrays.asList(
            "20190701,0900,1800,480,0,2019-07-09T16:52:31.675",
            "20190702,0900,1200,180,0,2019-07-09T16:52:31.675",
            "20190703,0900,1210,180,0,2019-07-09T16:52:31.675",
            "20190704,0900,1830,480,0,2019-07-09T16:52:31.675",
            "20190705,0900,1900,480,0,2019-07-09T16:52:31.675",
            "20190708,0900,2059,599,119,2019-07-09T16:52:31.675",
            "20190709,0900,2100,600,120,2019-07-09T16:52:31.675",
            "20190710,0900,2159,600,120,2019-07-09T16:52:31.675",
            "20190711,0900,2200,600,120,2019-07-09T16:52:31.675",
            "20190712,0900,2250,650,170,2019-07-09T16:52:31.675",
            "20190716,0900,2300,660,180,2019-07-09T16:52:31.675",
            "20190717,0800,1800,540,60,2019-07-09T16:52:31.675",
            "20190718,0800,1900,540,60,2019-07-09T16:52:31.675",
            "20190719,0900,1800,480,0,2019-07-09T16:52:31.675",
            "20190722,0900,1800,480,0,2019-07-09T16:52:31.675",
            "20190723,0900,1800,480,0,2019-07-09T16:52:31.675",
            "20190724,0900,1800,480,0,2019-07-09T16:52:31.675",
            "20190725,0900,1800,480,0,2019-07-09T16:52:31.675",
            "20190726,0900,1800,480,0,2019-07-09T16:52:31.675",
            "20190729,0900,1800,480,0,2019-07-09T16:52:31.675",
            "20190730,0900,1800,480,0,2019-07-09T16:52:31.675",
            "20190731,0900,1800,480,0,2019-07-09T16:52:31.675"
    );

    private static final String FILE_NAME = "data.csv";

    @Before
    public void setUp() throws Exception {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
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
    public void inputAttendance() throws Exception {
        AttendanceInputTime attendanceInputTime = mock(AttendanceInputTime.class);
        when(attendanceInputTime.now()).thenReturn("2019-07-09T16:52:31.675");

        for (String input : inputs) {
            String[] params = input.split(" ");

            // 勤務日の生成
            WorkingDay workingDay = new WorkingDay(params[0]);

            // 勤務開始時刻の生成
            StartTime startTime = new StartTime(params[1]);

            // 勤務終了時刻の生成
            EndTime endTime = new EndTime(params[2]);

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

        List<String> actual = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
        Assert.assertEquals(
                String.join("\n", expected),
                String.join("\n", actual));
    }
}
