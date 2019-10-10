package com.naosim.dddwork.datasouce;


import com.naosim.dddwork.domain.IKintaiDataRepository;
import com.naosim.dddwork.domain.KintaiData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CsvKintaiDataRepository implements IKintaiDataRepository {
    @Override
    public void save(KintaiData kintai) {
        try {
            CsvWriter.writeCsv(kintai);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<KintaiData> findKintaiDataByMonth(String yymm) {
        List<KintaiData> result = new LinkedList<>();
        Map<String, KintaiData> map = new HashMap<>();

        try {
            CsvReader reader = new CsvReader();

            String[] columns;
            while ((columns = reader.getColumns()) != null) {
                if (!columns[0].startsWith(yymm)) {
                    continue;
                }
                LocalDate date = createLocalDate(columns[0]);//YYYYMMDD
                LocalTime start = createLocalTime(columns[1]);//HHMM
                LocalTime end = createLocalTime(columns[2]);//HHMM
                KintaiData kintai = new KintaiData(date, start, end);
                map.put(kintai.getDate(), kintai);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (KintaiData k : map.values()) {
            result.add(k);
        }
        return result;
    }

    @Override
    public List<KintaiData> findKintaiDataByMonth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMM");
        return findKintaiDataByMonth(date.format(formatter));
    }

    private static LocalTime createLocalTime(String hhmm) {
        int hour = Integer.valueOf(hhmm.substring(0, 2));
        int minute = Integer.valueOf(hhmm.substring(2, 4));
        return LocalTime.of(hour, minute);
    }

    private static LocalDate createLocalDate(String yyyymmdd) {
        int year = Integer.valueOf(yyyymmdd.substring(0, 4));
        int mouth = Integer.valueOf(yyyymmdd.substring(4, 6));
        int day = Integer.valueOf(yyyymmdd.substring(6, 8));
        return LocalDate.of(year, mouth, day);
    }
}
