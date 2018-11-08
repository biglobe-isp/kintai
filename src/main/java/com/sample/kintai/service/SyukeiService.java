package com.sample.kintai.service;

import com.sample.kintai.domain.CsvFileInterFace;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SyukeiService {
    public void syukei(String yearMonth, CsvFileInterFace ds) {

        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;
        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

        String line = ds.read();
        while (line != null) {
            String[] columns = line.split(",");
            if (!columns[0].startsWith(yearMonth)) {
                continue;
            }
            totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
            totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

            line = ds.read();
        }

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for (String key : keySet) {
            totalWorkMinutes += totalWorkMinutesMap.get(key);
            totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }

        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
    }
}
