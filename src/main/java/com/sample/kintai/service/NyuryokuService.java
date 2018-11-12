package com.sample.kintai.service;

import com.sample.kintai.domain.CsvFileWriterInterface;
import com.sample.kintai.domain.EndTime;
import com.sample.kintai.domain.StartTime;
import com.sample.kintai.domain.WorkMinutes;

import java.time.LocalDateTime;

public class NyuryokuService {
    public void nyuryoku(String[] args, CsvFileWriterInterface csvFileInterface) {
        String date = args[1];
        String start = args[2];
        String end = args[3];
        String now = LocalDateTime.now().toString();

        StartTime startTime = new StartTime(start);
        EndTime endTime = new EndTime(end);
        WorkMinutes workMinutes = new WorkMinutes(startTime, endTime);

        csvFileInterface.write(
                date, start, end, workMinutes, now
        );

    }
}
