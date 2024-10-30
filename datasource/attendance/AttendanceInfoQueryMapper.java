package com.naosim.dddwork.datasource.attendance;

import com.naosim.dddwork.domain.attendance.input.AttendanceInfoEntity;
import com.naosim.dddwork.domain.attendance.input.WorkDate;
import com.naosim.dddwork.domain.attendance.total.WorkMonth;

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
import java.util.List;

public class AttendanceInfoQueryMapper {
    private final String FILE_NAME = "data.csv";

    public AttendanceInfoEntityCore regist(AttendanceInfoEntity entity) {
        File file = new File(FILE_NAME);
        LocalDateTime now = LocalDateTime.now();

        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    entity.getWorkDate().getValue().toString(),
                    entity.getWorkStartTime().getValue().toString(),
                    entity.getWorkEndTime().getValue().toString(),
                    entity.getWorkTime().getValue(),
                    entity.getOverWorkTime().getValue(),
                    now
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new AttendanceInfoEntityCore(
                entity.getWorkDate().getValue(),
                entity.getWorkStartTime().getValue(),
                entity.getWorkEndTime().getValue(),
                entity.getWorkTime().getValue(),
                entity.getOverWorkTime().getValue(),
                now
        );
    }

    public AttendanceInfoEntityCore update(AttendanceInfoEntity entity) throws Exception {
        File file = new File(FILE_NAME);
        LocalDateTime now = LocalDateTime.now();

        //まずはファイルのすべての内容を読み込み、リストに保存する
        ArrayList<String> fileContents = new ArrayList<>();
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                //更新対象の行は新しい内容を入れる
                if (columns[0].startsWith(entity.getWorkDate().getValue().toString())) {
                    fileContents.add(String.format(
                            "%s,%s,%s,%s,%s,%s\n",
                            entity.getWorkDate().getValue(),
                            entity.getWorkStartTime().getValue(),
                            entity.getWorkEndTime().getValue(),
                            entity.getWorkTime().getValue(),
                            entity.getOverWorkTime().getValue(),
                            now
                    ));
                } else {
                    fileContents.add(line + "\n");
                }

                line = br.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //更新した内容で新しく書き出す
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
        for (String line : fileContents) {
            bw.write(line);
        }
        bw.flush();
        bw.close();

        return new AttendanceInfoEntityCore(
                entity.getWorkDate().getValue(),
                entity.getWorkStartTime().getValue(),
                entity.getWorkEndTime().getValue(),
                entity.getWorkTime().getValue(),
                entity.getOverWorkTime().getValue(),
                now
        );
    }

    public AttendanceInfoEntityCore selectByDate(WorkDate date) throws Exception {
        AttendanceInfoEntityCore result = null;

        File file = new File(FILE_NAME);
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (columns[0].startsWith(date.getValue().toString())) {
                    result = new AttendanceInfoEntityCore(
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

    public List<AttendanceInfoEntityCore> selectByMonth(WorkMonth workMonth) throws Exception {
        File file = new File(FILE_NAME);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<AttendanceInfoEntityCore> result = new ArrayList<>();

        String searchTargetMonthString = workMonth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String line = br.readLine();
        while (line != null) {
            String[] columns = line.split(",");
            if (columns[0].startsWith(searchTargetMonthString)) {
                result.add(new AttendanceInfoEntityCore(
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

    public List<AttendanceInfoEntityCore> selectAll() throws Exception {
        File file = new File(FILE_NAME);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<AttendanceInfoEntityCore> result = new ArrayList<>();

        String line = br.readLine();
        while (line != null) {
            String[] columns = line.split(",");
            result.add(new AttendanceInfoEntityCore(
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
