package refactor.datasource;

import refactor.domain.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileRepository implements AttendanceRepository {
    private static final String FILE_NAME = "data.csv";

    public void save(DailyAttendanceRecord dailyAttendanceRecord) {
        try (FileWriter filewriter = new FileWriter(new File(FILE_NAME), true)) {
            filewriter.write(
                    String.format("%s,%s,%s,%s,%s,%s\n",
                            dailyAttendanceRecord.getDate().inYYYMMDD(),
                            dailyAttendanceRecord.getStartTime().inHHMM(),
                            dailyAttendanceRecord.getEndTime().inHHMM(),
                            dailyAttendanceRecord.getWorkingHours().inMinutes(),
                            dailyAttendanceRecord.getOvertimeHours().inMinuts(),
                            dailyAttendanceRecord.getCurrentTime().now()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
