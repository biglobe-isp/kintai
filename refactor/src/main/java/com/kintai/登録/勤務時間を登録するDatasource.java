package com.kintai.登録;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class 勤務時間を登録するDatasource {
    public static void 登録する(
            String date,
            String start,
            String end,
            int workMinutes,
            int overWorkMinutes,
            String now
    ) {
        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
