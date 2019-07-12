package refactor.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import refactor.api.AttendanceApi;

public class AttendanceInputServiceTest {
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

    @Before
    public void setUp() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @After
    public void tearDown() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void inputAttendance() throws Exception {
        inputs.stream().forEach(input -> AttendanceApi.main(input.split(" ")));

        Path path = Paths.get(FILE_NAME);
        long dailyAttendanceRecords = Files.lines(path).count();
        Assert.assertEquals(inputs.size(), dailyAttendanceRecords);
    }
}
