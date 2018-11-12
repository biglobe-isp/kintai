package com.sample.kintai.datasource;

import com.sample.kintai.domain.CsvFileReaderInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvFileReader implements CsvFileReaderInterface {
    private final FileReader fr;
    private final BufferedReader br;

    public CsvFileReader(FileReader fr, BufferedReader br) {
        this.fr = fr;
        this.br = br;
    }

    @Override
    public String read() {
        String line = null;
        try {
            line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
