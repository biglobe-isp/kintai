package refactor.datasource;

import refactor.domain.CsvWriterRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter implements CsvWriterRepo {
    public void registerWorkingTime(
            String date,
            String start,
            String end,
            int workMinutes,
            int overWorkMinutes,
            String now) {
        java.io.File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
