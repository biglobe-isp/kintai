package com.naosim.dddwork.kintai.datasource.csv.entity;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Data
public class AttendanceRecordEntity {
    @CsvDate("yyyy-MM-dd")
    @CsvBindByPosition(position = 0)
    private LocalDate ymd;

    @CsvDate("HH:mm")
    @CsvBindByPosition(position = 1)
    private LocalTime startTime;

    @CsvDate("HH:mm")
    @CsvBindByPosition(position = 2)
    private LocalTime endTime;

    @CsvBindByPosition(position = 3)
    private int workingTimeMinutes;

    @CsvBindByPosition(position = 4)
    private int overtimeMinutes;

    @CsvDate("yyyy-MM-dd'T'HH:mm:ss.SSSXXX'['VV']'")
    @CsvBindByPosition(position = 5)
    private ZonedDateTime updatedAt;

}
