package com.naosim.dddwork.service;

import com.naosim.dddwork.datasouce.CsvWriter;
import domain.KintaiCalcurator;

import java.time.LocalDateTime;

public class InputKintai {
    public static void input(String date, String start, String end) {
        KintaiCalcurator kintai = new KintaiCalcurator(date, start, end);
        
        CsvWriter.writeCsv(date, kintai);

    }
}
