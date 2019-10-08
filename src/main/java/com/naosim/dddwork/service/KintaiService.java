package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.KintaiCalcurator;
import com.naosim.dddwork.domain.KintaiRepository;

import java.util.List;

public class KintaiService {
    public static void input(String date, String start, String end) {
        KintaiCalcurator kintai = new KintaiCalcurator(date, start, end);

        KintaiRepository repository = new KintaiRepository();
        repository.save(kintai);
    }

    public static int[] total(String yymm) {
        KintaiRepository repository = new KintaiRepository();
        List<KintaiCalcurator> list = repository.findKintai(yymm);
        int totalWorkTime = 0;
        int totalOverWorkTime = 0;
        for (KintaiCalcurator k : list) {
            totalWorkTime += Integer.valueOf(k.getWorkMinutes());
            totalOverWorkTime += Integer.valueOf(k.getOverWorkMinutes());
        }
        return new int[]{totalWorkTime, totalOverWorkTime};
    }
}
