package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class KintaiTotalPrintService {

    @Autowired
    KintaiListRepository kintaiListRepository;

    @Autowired
    KintaiTotalPrintRepository kintaiTotalPrintRepository;

    public void printTargetMonth(KintaiTotalPrintInput kintaiTotalPrintInput) throws IOException {
        KintaiLines kintaiLines = this.kintaiListRepository.get();
        KintaiTotal kintaiTotal = new KintaiTotal(kintaiTotalPrintInput, kintaiLines);

        this.kintaiTotalPrintRepository.print(kintaiTotal);
    }
}
