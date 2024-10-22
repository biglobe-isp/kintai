package com.naosim.dddwork.datasource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class WorkTimeCSV {
    private final String FILE_NAME = "data.csv";


    public WorkTimeEntity Regist(WorkTimeEntity entity) throws Exception{
        File file = new File(FILE_NAME);
        FileWriter filewriter = new FileWriter(file, true);
        filewriter.write(String.format(
                "%s,%s,%s,%s,%s,%s\n",
                entity.date,
                entity.startTime,
                entity.endTime,
                entity.workMinutes,
                entity.overWorkMinutes,
                entity.now
        ));

        return entity;
    }
    public WorkTimeEntity Update(WorkTimeEntity entity) throws Exception{
        //省略
        return entity;
    }
    public WorkTimeEntity SelectByDate(LocalDate date) throws Exception{
        File file = new File(FILE_NAME);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        WorkTimeEntity result = null;

        String line = br.readLine();
        while (line != null) {
            String[] columns = line.split(",");
            if (columns[0].startsWith(date.toString())) {
                result = new WorkTimeEntity(
                        LocalDate.parse(columns[0]),
                        LocalTime.parse(columns[1]),
                        LocalTime.parse(columns[2]),
                        Integer.valueOf(columns[3]),
                        Integer.valueOf(columns[4]),
                        LocalDateTime.parse(columns[5])
                );

                break;
            }

            line = br.readLine();
        }

        return result;
    }
    public ArrayList<WorkTimeEntity> SelectByMonth(LocalDate date) throws Exception{
        File file = new File(FILE_NAME);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<WorkTimeEntity> result = new ArrayList<>();

        String line = br.readLine();
        while (line != null) {
            String[] columns = line.split(",");
            if (columns[0].startsWith(new SimpleDateFormat("yyyyMM").format(date))) {
                result.add(new WorkTimeEntity(
                                LocalDate.parse(columns[0]),
                                LocalTime.parse(columns[1]),
                                LocalTime.parse(columns[2]),
                                Integer.valueOf(columns[3]),
                                Integer.valueOf(columns[4]),
                                LocalDateTime.parse(columns[5])
                        )
                );
            }

            line = br.readLine();
        }

        return result;
    }
}
