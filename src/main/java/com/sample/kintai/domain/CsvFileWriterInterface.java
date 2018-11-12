package com.sample.kintai.domain;

public interface CsvFileWriterInterface {
    void write(String date, String start, String end, WorkMinutes workMinutes, String now);
}
