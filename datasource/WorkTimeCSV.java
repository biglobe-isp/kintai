package com.naosim.dddwork.datasource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WorkTimeCSV implements WorkTimeRepository {
    private final String FILE_NAME = "data.csv";

    public WorkTimeEntity regist(WorkTimeEntity entity) throws Exception {
        File file = new File(FILE_NAME);
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    entity.date.toString(),
                    entity.startTime.toString(),
                    entity.endTime.toString(),
                    entity.workMinutes.toString(),
                    entity.overWorkMinutes.toString(),
                    entity.now.toString()
            ));
        }

        return entity;
    }

    public WorkTimeEntity update(WorkTimeEntity entity) throws Exception {
        ArrayList<String> fileContents = new ArrayList<>();

        //まずはファイルのすべての内容を読み込み、リストに保存する
        File file = new File(FILE_NAME);
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                //更新対象の行は新しい内容を入れる
                if (columns[0].startsWith(entity.date.toString())) {
                    fileContents.add(String.format(
                            "%s,%s,%s,%s,%s,%s\n",
                            entity.date.toString(),
                            entity.startTime.toString(),
                            entity.endTime.toString(),
                            entity.workMinutes.toString(),
                            entity.overWorkMinutes.toString(),
                            entity.now.toString()
                    ));
                } else {
                    fileContents.add(line + "\n");
                }

                line = br.readLine();
            }
        }

        //更新した内容で新しく書き出す
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
        for (String line : fileContents) {
            bw.write(line);
        }
        bw.flush();
        bw.close();

        return entity;
    }

    public WorkTimeEntity selectByDate(LocalDate date) throws Exception {
        WorkTimeEntity result = null;

        File file = new File(FILE_NAME);
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
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
        }

        return result;
    }

    public ArrayList<WorkTimeEntity> selectByMonth(LocalDate date) throws Exception {
        File file = new File(FILE_NAME);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<WorkTimeEntity> result = new ArrayList<>();

        String line = br.readLine();
        while (line != null) {
            String[] columns = line.split(",");
            if (columns[0].startsWith(date.format(DateTimeFormatter.ofPattern("yyyy-MM")))) {
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

    public ArrayList<WorkTimeEntity> selectAll() throws Exception {
        File file = new File(FILE_NAME);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<WorkTimeEntity> result = new ArrayList<>();

        String line = br.readLine();
        while (line != null) {
            String[] columns = line.split(",");
            result.add(new WorkTimeEntity(
                               LocalDate.parse(columns[0]),
                               LocalTime.parse(columns[1]),
                               LocalTime.parse(columns[2]),
                               Integer.valueOf(columns[3]),
                               Integer.valueOf(columns[4]),
                               LocalDateTime.parse(columns[5])
                       )
            );

            line = br.readLine();
        }

        return result;
    }
}
