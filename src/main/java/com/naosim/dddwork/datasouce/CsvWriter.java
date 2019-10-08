package com.naosim.dddwork.datasouce;

import domain.KintaiCalcurator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class CsvWriter {

    public static void writeCsv(String date, KintaiCalcurator kintai) {
        File file = new File("data.csv");
        String now = LocalDateTime.now().toString();
        try (FileWriter filewriter = new FileWriter(file, true)) {
            //TODO datasource層がDomain層に依存している。依存関係を逆転させる。
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, kintai.getStart(), kintai.getEnd(), kintai.getWorkMinutes(),
                    kintai.getOverWorkMinutes(), now));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
