package com.naosim.dddwork.datasouce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
    private BufferedReader br;

    public CsvReader() throws IOException {
        File file = new File("data.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        this.br = br;
    }

    public String[] getColumns() throws IOException {
        String row = br.readLine();
        if (row == null) {
            return null;
        }
        return row.split(",");
    }
}
