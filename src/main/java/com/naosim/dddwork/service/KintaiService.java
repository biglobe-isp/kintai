package com.naosim.dddwork.service;

import com.naosim.dddwork.datasouce.CsvKintaiDataRepository;
import com.naosim.dddwork.domain.IKintaiDataRepository;
import com.naosim.dddwork.domain.KintaiData;
import com.naosim.dddwork.domain.MonthlyTotalWorkTime;

import java.util.List;

public class KintaiService {
    public static void input(String date, String start, String end) {
        KintaiData kintai = new KintaiData(date, start, end);

        IKintaiDataRepository repository = new CsvKintaiDataRepository();
        repository.save(kintai);
    }

    public static MonthlyTotalWorkTime total(String yymm) {
        IKintaiDataRepository repository = new CsvKintaiDataRepository();
        List<KintaiData> list = repository.findKintaiDataByMonth(yymm);
        MonthlyTotalWorkTime result = new MonthlyTotalWorkTime(list);

        return result;
    }
}
