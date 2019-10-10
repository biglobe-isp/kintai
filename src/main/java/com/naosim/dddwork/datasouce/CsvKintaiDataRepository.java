package com.naosim.dddwork.datasouce;


import com.naosim.dddwork.domain.IKintaiDataRepository;
import com.naosim.dddwork.domain.KintaiData;

import java.io.IOException;
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

    @Override
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
