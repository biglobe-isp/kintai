package com.naosim.dddwork.datasouce;

import domain.KintaiCalcurator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    public static void writeCsv(String date, KintaiCalcurator kintai, String now) {
        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            //TODO datasource層がDomain層に依存している。依存関係を逆転させる。
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, kintai.getStart(), kintai.getEnd(), kintai.getWorkMinutes(),
                    kintai.getOverWorkMinutes(), now));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
