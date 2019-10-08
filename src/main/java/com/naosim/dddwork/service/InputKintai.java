package com.naosim.dddwork.service;

import com.naosim.dddwork.datasouce.CsvWriter;
import domain.KintaiCalcurator;

import java.time.LocalDateTime;

public class InputKintai {
    public static void input(String date, String start, String end) {
        KintaiCalcurator kintai = new KintaiCalcurator(date, start, end);

        //[TODO] nowはCSVWriteに移動する（書き込んだ日時）
        String now = LocalDateTime.now().toString();
        CsvWriter.writeCsv(date, kintai, now);

    }
}
