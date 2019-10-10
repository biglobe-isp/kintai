package com.naosim.dddwork.service;

import com.naosim.dddwork.datasouce.CsvKintaiDataRepository;
import com.naosim.dddwork.domain.IKintaiDataRepository;
import com.naosim.dddwork.domain.KintaiData;
import com.naosim.dddwork.domain.MonthlyTotalWorkTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class KintaiService {

    public static void input(LocalDate date, LocalTime start, LocalTime end) {
        KintaiData kintai = createKintaiData(date, start, end);
        IKintaiDataRepository repository = new CsvKintaiDataRepository();
        repository.save(kintai);
    }

    public static MonthlyTotalWorkTime total(LocalDate yymm) {
        IKintaiDataRepository repository = new CsvKintaiDataRepository();
        List<KintaiData> list = repository.findKintaiDataByMonth(yymm);
        MonthlyTotalWorkTime result = new MonthlyTotalWorkTime(list);

        return result;
    }

    private static KintaiData createKintaiData(LocalDate date, LocalTime start, LocalTime end) {
        return new KintaiData(date, start, end);
    }
}
