package com.kintai.表示;

import java.util.Map;
import java.util.Set;

public class 勤務時間を表示するDomain {

    public static void 表示する(
            Map<String, Integer> totalWorkMinutesMap,
            Map<String, Integer> totalOverWorkMinutesMap
    ) {
        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;
        Set<String> keySet = totalWorkMinutesMap.keySet();
        for (String key : keySet) {
            totalWorkMinutes += totalWorkMinutesMap.get(key);
            totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }

        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
    }
}
