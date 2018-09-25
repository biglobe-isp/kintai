package com.naosim.dddwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    private static int getWorkMinutes(String start, String end) {

        int startH = Integer.valueOf(start.substring(0, 2));
        int startM = Integer.valueOf(start.substring(2, 4));

        int endH = Integer.valueOf(end.substring(0, 2));
        int endM = Integer.valueOf(end.substring(2, 4));

        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= 60;
        }

        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= 60;
        }

        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= 60;
        }

        return workMinutes;
    }

    private static int getOverWorkMinutes(int workMinutes) {

        return Math.max(workMinutes - 8 * 60, 0);
    }

    public static void main(String[] args) {

        try {

            MethodType methodType = new MethodType(args);

            if (methodType.isInput()) {

                System.out.println("isInput");

                String date = args[1];
                String start = args[2];
                String end = args[3];

                int workMinutes = getWorkMinutes(start, end);

                int overWorkMinutes = getOverWorkMinutes(workMinutes);

                File file = new File("data.csv");

                try (FileWriter filewriter = new FileWriter(file, true)) {

                    filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, LocalDateTime.now().toString()));

                    System.out.println(String.format("write: %s,%s,%s,%s,%s,%s", date, start, end, workMinutes, overWorkMinutes, LocalDateTime.now().toString()));

                }

            } else if (methodType.isTotal()) {

                System.out.println("isTotal");

                String yearMonth = args[1];

                int totalWorkMinutes = 0;
                int totalOverWorkMinutes = 0;

                File file = new File("data.csv");

                try (
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                ) {

                    String line = br.readLine();
                    Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
                    Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
                    while (line != null) {
                        String[] columns = line.split(",");
                        if (!columns[0].startsWith(yearMonth)) {
                            continue;
                        }
                        totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                        totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                        line = br.readLine();
                    }

                    Set<String> keySet = totalWorkMinutesMap.keySet();
                    for (String key : keySet) {
                        totalWorkMinutes += totalWorkMinutesMap.get(key);
                        totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
                    }

                    System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
                    System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
                }

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
