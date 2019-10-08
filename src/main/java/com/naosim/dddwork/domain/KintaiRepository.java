package com.naosim.dddwork.domain;

import com.naosim.dddwork.datasouce.CsvWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KintaiRepository {


    /***
     * 勤怠データを保存する
     * @param kintai
     */
    public void save(KintaiCalcurator kintai) {
        CsvWriter.writeCsv(kintai);
    }

    /***
     * 年月を元に勤怠データを取得する。
     * @return
     */
    public List<KintaiCalcurator> findKintai(String yymm) {
        List<KintaiCalcurator> result = new LinkedList<>();
        Map<String, KintaiCalcurator> map = new HashMap<>();
        File file = new File("data.csv");
        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(yymm)) {
                    continue;
                }
                KintaiCalcurator kintai = new KintaiCalcurator(columns[0], columns[1], columns[2]);
                map.put(kintai.getDate(), kintai);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (KintaiCalcurator k : map.values()) {
            result.add(k);
        }
        return result;

    }
}
