package src.com.naosim.dddwork.service;

import java.util.*;

import src.com.naosim.dddwork.datasource.CsvOperat;
import src.com.naosim.dddwork.domain.*;

public class TotalService {
    KintaiRepository repository;

    public void total(String yearMonth) {
        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;
        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

        KintaiRepository repository = new CsvOperat();
        List<String[]> readData = repository.read();

        for(String[] columns : readData){
            if(!columns[0].startsWith(yearMonth)) {
                continue;
            }
            totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
            totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));
        }

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for(String key : keySet) {
            totalWorkMinutes += totalWorkMinutesMap.get(key);
            totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }

        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
    }
}