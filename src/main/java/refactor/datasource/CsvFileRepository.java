package refactor.datasource;

import refactor.domain.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileRepository implements Repository {
    private static final String FILE_NAME = "data.csv";

    public void save(
            Date date,
            StartTime startTime,
            EndTime endTime,
            WorkingHours workingHours,
            OvertimeHours overtimeHours,
            CurrentTime currentTime) {
        try (FileWriter filewriter = new FileWriter(new File(FILE_NAME), true)) {
            filewriter.write(
                    String.format("%s,%s,%s,%s,%s,%s\n",
                            date.inYYYMMDD(),
                            startTime.inHHMM(),
                            endTime.inHHMM(),
                            workingHours.inMinutes(),
                            overtimeHours.inMinuts(),
                            currentTime.now()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
