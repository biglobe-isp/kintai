package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KintaiTotalPrintService {

    @Autowired
    KintaiListRepository kintaiListRepository;

    @Autowired
    KintaiTotalPrintRepository kintaiTotalPrintRepository;

    public void printTargetMonth(KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth) {
        KintaiOfOneDayLines kintaiOfOneDayLines = this.kintaiListRepository.get();
        KintaiTotal kintaiTotal = new KintaiTotal(kintaiTotalPrintTargetYearMonth, kintaiOfOneDayLines);

        this.kintaiTotalPrintRepository.print(kintaiTotal);
    }
}
