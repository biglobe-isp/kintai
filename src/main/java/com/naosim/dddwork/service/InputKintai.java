package com.naosim.dddwork.service;

import com.naosim.dddwork.datasouce.CsvWriter;
import domain.KintaiCalcurator;

import java.time.LocalDateTime;

public class InputKintai {
    public static void input(String date, int startHour, int startMinute, int endHour, int endMinute) {
        KintaiCalcurator kintai = new KintaiCalcurator(date, startHour, startMinute, endHour, endMinute);
        kintai.calcurateJiturodo();
        kintai.calcurateZangyo();

        //[TODO] nowはCSVWriteに移動する（書き込んだ日時）
        String now = LocalDateTime.now().toString();
        CsvWriter.writeCsv(date, kintai, now);

    }
}
