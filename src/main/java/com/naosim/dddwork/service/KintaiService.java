package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.KintaiData;
import com.naosim.dddwork.domain.KintaiRepository;
import com.naosim.dddwork.domain.MonthlyTotalWorkTime;

import java.util.List;

public class KintaiService {
    public static void input(String date, String start, String end) {
        KintaiData kintai = new KintaiData(date, start, end);

        KintaiRepository repository = new KintaiRepository();
        repository.save(kintai);
    }

    public static MonthlyTotalWorkTime total(String yymm) {
        KintaiRepository repository = new KintaiRepository();
        List<KintaiData> list = repository.findKintaiDataByMonth(yymm);
        MonthlyTotalWorkTime result = new MonthlyTotalWorkTime(list);

        return result;
    }
}
