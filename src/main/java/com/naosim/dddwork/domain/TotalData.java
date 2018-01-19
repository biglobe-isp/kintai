package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TotalData extends ProcessData {

    public TotalData(InputData inputData) {
        super(inputData);
    }

    public void printTotalData(List<String> registLineList) {
        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

        for (String line : registLineList) {
            String[] columns = line.split(",");
            if(!columns[0].startsWith(inputData.getYearMonth())) {
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

        // TODO: domainで副作用があるのは良くないので、以下の出力処理はserviceで行うよう修正する
        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");

    }
}
