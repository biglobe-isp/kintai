package com.naosim.dddwork.kintai.service.command;

import com.naosim.dddwork.kintai.shared.exception.SystemException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class DailyWorkTimeRegistration {

    public void execute(String[] args) {

        try {
            _registerWorkTime(args);
        }
        catch (IOException e) {
            throw new SystemException("日次勤怠登録処理中に入出力例外が発生しました．", e);
        }
    }

    private static void _registerWorkTime(String[] args) throws IOException {

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

        if (endH > 23) {
            final int serviceH = endH - 24;
            final int serviceM = endM;
            workMinutes -= serviceH * 60 + serviceM;
        }

        int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
        File file = new File("data.csv");
        try(FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));
        }
    }
}
