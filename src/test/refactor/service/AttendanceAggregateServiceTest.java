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
            "input 20190701 0900 1800",
            "input 20190702 0900 1200",
            "input 20190703 0900 1210",
            "input 20190704 0900 1830",
            "input 20190705 0900 1900",
            "input 20190708 0900 2059",
            "input 20190709 0900 2100",
            "input 20190710 0900 2159",
            "input 20190711 0900 2200",
            "input 20190712 0900 2250",
            "input 20190716 0900 2300",
            "input 20190717 0900 1800",
            "input 20190718 0900 1900",
            "input 20190719 0900 1800",
            "input 20190701 0900 1700",
            "input 20190722 0900 1800",
            "input 20190723 0900 1800",
            "input 20190724 0900 1800",
            "input 20190725 0900 1800",
            "input 20190726 0900 1800",
            "input 20190729 0900 1800",
            "input 20190730 0900 1800",
            "input 20190731 0900 1800",
            "input 20190801 0900 1700",
            "input 20190802 0900 2000"
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
        for (String input : inputs) {
            String[] args = input.split(" ");
            AttendanceApi.main(args);
        }
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