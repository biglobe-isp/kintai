package refoctor.datasource;

import refoctor.domain.DayWorkMinutesRepository;

import java.io.File;
import java.io.FileWriter;

public class DayWorkMinutesDb implements DayWorkMinutesRepository {

    public void dayOutPut(String date, String start, String end, int workMinutes, int overWorkMinutes, String now) {
        File file = new File("data.csv");

        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
