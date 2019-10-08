package com.naosim.dddwork.datasouce;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class CsvWriter {

    public static void writeCsv(IKintai kintai) {
        File file = new File("data.csv");
        String now = LocalDateTime.now().toString();
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", kintai.getDate(), kintai.getStart(), kintai.getEnd(),
                    kintai.getWorkMinutes(),
                    kintai.getOverWorkMinutes(), now));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
