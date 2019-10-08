package com.naosim.dddwork.domain;

import com.naosim.dddwork.datasouce.CsvReader;
import com.naosim.dddwork.datasouce.CsvWriter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KintaiRepository {


    /***
     * 勤怠データを保存する
     * @param kintai
     */
    public void save(KintaiData kintai) {
        CsvWriter.writeCsv(kintai);
    }

    /***
     * 年月を元に勤怠データを取得する。
     * @return
     */
    public List<KintaiData> findKintaiDataByMonth(String yymm) {
        List<KintaiData> result = new LinkedList<>();
        Map<String, KintaiData> map = new HashMap<>();

        try {
            CsvReader reader = new CsvReader();

            String[] columns;
            while ((columns = reader.getColumns()) != null) {
                if (!columns[0].startsWith(yymm)) {
                    continue;
                }
                KintaiData kintai = new KintaiData(columns[0], columns[1], columns[2]);
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
}
