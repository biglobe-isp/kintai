package refactor.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import refactor.api.AttendanceApi;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class AttendanceAggregateServiceTest {
    private List<String> inputs = Arrays.asList(
            "input -date:20190701 -start:09_00 -end:18_00",
            "input -date:20190702 -start:09_00 -end:12_00",
            "input -date:20190703 -start:09_00 -end:12_10",
            "input -date:20190704 -start:09_00 -end:18_30",
            "input -date:20190705 -start:09_00 -end:19_00",
            "input -date:20190708 -start:09_00 -end:20_59",
            "input -date:20190709 -start:09_00 -end:21_00",
            "input -date:20190710 -start:09_00 -end:21_59",
            "input -date:20190711 -start:09_00 -end:22_00",
            "input -date:20190712 -start:09_00 -end:22_50",
            "input -date:20190716 -start:09_00 -end:23_00",
            "input -date:20190717 -start:09_00 -end:18_00",
            "input -date:20190718 -start:09_00 -end:19_00",
            "input -date:20190719 -start:09_00 -end:18_00",
            "input -date:20190701 -start:09_00 -end:17_00",
            "input -date:20190722 -start:09_00 -end:18_00",
            "input -date:20190723 -start:09_00 -end:18_00",
            "input -date:20190724 -start:09_00 -end:18_00",
            "input -date:20190725 -start:09_00 -end:18_00",
            "input -date:20190726 -start:09_00 -end:18_00",
            "input -date:20190729 -start:09_00 -end:18_00",
            "input -date:20190730 -start:09_00 -end:18_00",
            "input -date:20190731 -start:09_00 -end:18_00",
            "input -date:20190801 -start:09_00 -end:17_00",
            "input -date:20190802 -start:09_00 -end:20_00"
    );

    private static final String FILE_NAME = "data.csv";

    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;

    @Before
    public void setUp() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }

        // 標準出力の出力先を変更
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = System.out;
        System.setOut(new PrintStream(new BufferedOutputStream(byteArrayOutputStream)));

        // テスト用data.csvを作成
        inputs.stream().forEach(input -> AttendanceApi.main(input.split(" ")));
    }

    @After
    public void tearDown() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }

        System.setOut(printStream);
    }

    @Test
    public void calculateTotalWorkingHoursAndTotalOvertimeWorkingHours() {
        AttendanceApi.main(new String[]{"total", "201907"});
        System.out.flush();

        Assert.assertEquals("勤務時間: 178時間49分\n残業時間: 13時間49分\n", byteArrayOutputStream.toString());
    }
}