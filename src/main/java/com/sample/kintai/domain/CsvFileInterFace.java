package com.sample.kintai.domain;

public interface CsvFileInterFace {
    void write(String date, String start, String end, WorkMinutes workMinutes, String now);

    String read();
}
