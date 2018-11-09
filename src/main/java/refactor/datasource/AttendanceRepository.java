package refactor.datasource;

import refactor.datasource.entity.AttendanceData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AttendanceRepository {

    private final String FILE_NAME = "data.csv";

    public void insert(AttendanceData data){
        File file = new File(FILE_NAME);
        try(FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    data.getDate(),
                    data.getStartTime(),
                    data.getEndTime(),
                    data.getWorkMinutes(),
                    data.getOverWorkMinutes(),
                    data.getNowTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
