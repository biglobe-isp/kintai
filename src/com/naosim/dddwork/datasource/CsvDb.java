package com.naosim.dddwork.datasource;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class CsvDb {

    File file;

    public CsvDb() {
        this.file = new File("data.csv");
    }

    public void add(String[] arrBuf) {

        try (FileWriter filewriter = new FileWriter(this.file, true)) {
            filewriter.write(String.format("%s,%s\n", String.join(",", arrBuf), LocalDateTime.now().toString()));
        } catch (IOException e) {
            System.out.println("IOExceptionが発生");
        }
    }

    public List<String[]> read() {
        List<String[]> valueLines = new ArrayList();

        try(
                FileReader fr = new FileReader(this.file);
                BufferedReader br = new BufferedReader(fr);
        ) {
            String line = br.readLine();
            while(line != null){
                String[] columns = line.split(",");
                valueLines.add(columns);
                line = br.readLine();
            }
        } catch(IOException e) {
            System.out.println("IOExceptionが発生");
        }
        return valueLines;
    }

}
