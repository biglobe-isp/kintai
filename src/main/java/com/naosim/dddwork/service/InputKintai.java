package com.naosim.dddwork.service;

import com.naosim.dddwork.datasouce.CsvWriter;
import domain.KintaiCalcurator;

import java.time.LocalDateTime;

public class InputKintai {
    public static void input(String date, int startHour, int startMinute, int endHour, int endMinute) {
        KintaiCalcurator kintai = new KintaiCalcurator(startHour, startMinute, endHour, endMinute);
        kintai.calcurateJiturodo();
        kintai.calcurateZangyo();

        String now = LocalDateTime.now().toString();
        CsvWriter.writeCsv(date, kintai, now);

    }
}
