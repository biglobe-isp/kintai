package com.sample.kintai.service;

import com.sample.kintai.domain.CsvFileInterFace;
import com.sample.kintai.domain.EndTime;
import com.sample.kintai.domain.StartTime;
import com.sample.kintai.domain.WorkMinutes;

import java.time.LocalDateTime;

public class NyuryokuService {
    public void nyuryoku(String[] args, CsvFileInterFace csvFileInterFace) {
        String date = args[1];
        String start = args[2];
        String end = args[3];
        String now = LocalDateTime.now().toString();

        StartTime startTime = new StartTime(start);
        EndTime endTime = new EndTime(end);
        WorkMinutes workMinutes = new WorkMinutes(startTime, endTime);

        csvFileInterFace.write(
                date, start, end, workMinutes, now
        );

    }
}
