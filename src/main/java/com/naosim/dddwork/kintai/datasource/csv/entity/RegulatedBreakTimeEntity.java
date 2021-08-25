package com.naosim.dddwork.kintai.datasource.csv.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegulatedBreakTimeEntity {
    @CsvDate("yyyyMMdd")
    @CsvBindByName(column = "valid_start_date", required = true)
    private LocalDate validStartDate;

    @CsvDate("yyyyMMdd")
    @CsvBindByName(column = "valid_end_date", required = true)
    private LocalDate validEndDate;

    @CsvBindByName(column = "break_time_start", required = true)
    private String breakTimeStart;

    @CsvBindByName(column = "break_time_end", required = true)
    private String breakTimeEnd;
}
