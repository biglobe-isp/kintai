package com.kintai.表示;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 勤務時間を表示するDatasource {

        public static List<Map<String, Integer>> 時間を取得する(String yearMonth) {
        //ファイルから取得するDatasource
        File file = new File("data.csv");
        List<Map<String, Integer>> mapList = new ArrayList<>();

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {


            String line = br.readLine();
            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(yearMonth)) {
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();

                mapList.add(totalWorkMinutesMap);
                mapList.add(totalOverWorkMinutesMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(mapList.isEmpty()) {
            throw new RuntimeException("参照に失敗しました");
        }
        return mapList;
    }
}
