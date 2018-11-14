package refactor.datasource;

import refactor.domain.repository.AttendanceRepositoryInsert;
import refactor.domain.dto.AttendanceData;

import java.io.*;

public class AttendanceRepositoryInsertImpl implements AttendanceRepositoryInsert {

    private final String FILE_NAME = "data.csv";


    public void insert(AttendanceData data){
        File file = new File(FILE_NAME);
        try(FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    data.getDate().getDate(),
                    data.valueOfStartTimeString(),
                    data.valueOfEndTimeString(),
                    data.getWorkAndOverWorkMinutesItem().getWorkMinutes(),
                    data.getWorkAndOverWorkMinutesItem().getOverWorkMinutes(),
                    data.getNowTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
