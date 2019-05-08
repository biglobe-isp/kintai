package com.naosim.dddwork.kintai;

import com.naosim.dddwork.kintai.api.ArgumentParser;
import com.naosim.dddwork.kintai.api.Request;
import com.naosim.dddwork.kintai.api.Usage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        try {
            ArgumentParser parser = new ArgumentParser(args);
            Request request = parser.pickRequest();

            if (request == Request.INPUT) {
                _registerWorkTime(args);
            }
            else if(request == Request.TOTAL) {
                _showWorkedTime(args);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Usage.print();
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

    private static void _showWorkedTime(String[] args) throws IOException {

        String yearMonth = args[1];
        if(args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }

        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        File file = new File("data.csv");

        try(
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                ) {

            String line = br.readLine();
            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while(line != null){
                String[] columns = line.split(",");
                if(!columns[0].startsWith(yearMonth)) {
                    line = br.readLine();
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }

            Set<String> keySet = totalWorkMinutesMap.keySet();
            for(String key : keySet) {
                totalWorkMinutes += totalWorkMinutesMap.get(key);
                totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
            }

            System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
            System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
        }
    }

}