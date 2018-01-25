package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.kintai.totalprint.KintaiOfDays;
import com.naosim.dddwork.domain.kintai.totalprint.KintaiTotal;
import com.naosim.dddwork.domain.kintai.totalprint.KintaiTotalPrintTargetYearMonth;
import com.naosim.dddwork.domain.kintai.totalprint.repository.KintaiListRepository;
import com.naosim.dddwork.domain.kintai.totalprint.repository.KintaiTotalPrintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KintaiTotalPrintService {

    @Autowired
    KintaiListRepository kintaiListRepository;

    @Autowired
    KintaiTotalPrintRepository kintaiTotalPrintRepository;

    public void printTargetMonth(KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth) {
        KintaiOfDays kintaiOfDays = this.kintaiListRepository.get();
        KintaiTotal kintaiTotal = new KintaiTotal(kintaiTotalPrintTargetYearMonth, kintaiOfDays);

        this.kintaiTotalPrintRepository.print(kintaiTotal);
    }
}
