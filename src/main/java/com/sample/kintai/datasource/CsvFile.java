package com.sample.kintai.datasource;

import com.sample.kintai.domain.CsvFileInterFace;
import com.sample.kintai.domain.WorkMinutes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFile implements CsvFileInterFace {
    @Override
    public void write(String date, String start, String end, WorkMinutes workMinutes, String now) {
        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(
                    String.format(
                            "%s,%s,%s,%s,%s,%s\n",
                            date,
                            start,
                            end,
                            workMinutes.calcWorkMinutesIncludingBreakTime(),
                            workMinutes.calcOverWorkMinutes(),
                            now
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read() {
        File file = new File("data.csv");
        String line = null;
        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
