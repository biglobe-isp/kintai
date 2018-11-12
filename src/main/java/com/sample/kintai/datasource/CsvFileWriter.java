package com.sample.kintai.datasource;

import com.sample.kintai.domain.CsvFileWriterInterface;
import com.sample.kintai.domain.WorkMinutes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileWriter implements CsvFileWriterInterface {
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
}
