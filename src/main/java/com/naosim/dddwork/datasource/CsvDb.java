package com.naosim.dddwork.datasource;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CsvDb {
    File file;

    public CsvDb(String entityName) {
        this.file = new File(String.format("data/%s.csv", entityName));
    }

    public void add(List<String> buf) {

        try (FileWriter filewriter = new FileWriter(this.file, true)) {
            filewriter.write(String.format("%s,%s\n", String.join(",", buf), LocalDateTime.now().toString()));
        } catch (IOException e) {
            System.out.println("IOExceptionが発生");
        }
    }

    public List<List<String>> read() {
        List<List<String>> valueLines = new ArrayList();

        try (
                FileReader fr = new FileReader(this.file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            while (line != null) {
                List<String> columns = Arrays.asList(line.split(","));
                valueLines.add(columns);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("IOExceptionが発生");
        }
        return valueLines;
    }
}
