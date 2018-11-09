package com.kintai.表示;

import java.util.List;
import java.util.Map;

public class 勤務時間を表示するService {
    public static void 表示する(String yearMonth) {
        List<Map<String, Integer>> result = 勤務時間を表示するDatasource.時間を取得する(yearMonth);

        勤務時間を表示するDomain.表示する(result.get(0),result.get(1));

    }
}
