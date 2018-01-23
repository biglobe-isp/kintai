package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.WorkTimeRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class WorkTimeInputRepositoryFile implements WorkTimeRepository{
    @Override
    public Void doExecute(String[] args) {
        if(args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }
        String date = args[1];
        String start = args[2];
        String end = args[3];
        String now = LocalDateTime.now().toString();

        int startH = Integer.valueOf(start.substring(0, 2));
        int startM = Integer.valueOf(start.substring(2, 4));

        int endH = Integer.valueOf(end.substring(0, 2));
        int endM = Integer.valueOf(end.substring(2, 4));

        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if(endH == 12) {
            workMinutes -= endM;
        } else if(endH >= 13) {
            workMinutes -= 60;
        }

        if(endH == 18) {
            workMinutes -= endM;
        } else if(endH >= 19) {
            workMinutes -= 60;
        }

        if(endH == 21) {
            workMinutes -= endM;
        } else if(endH >= 22) {
            workMinutes -= 60;
        }

        int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
        File file = new File("data.csv");
        try(FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
