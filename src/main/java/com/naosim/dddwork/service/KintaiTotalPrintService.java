package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.KintaiTotalPrint;
import com.naosim.dddwork.domain.KintaiTotalPrintRepository;
import com.naosim.dddwork.domain.KintaiTotalPrintInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class KintaiTotalPrintService {

    @Autowired
    KintaiTotalPrintRepository kintaiTotalPrintRepository;

    public void printTargetMonth(KintaiTotalPrintInput kintaiTotalPrintInput) throws IOException {
        // TODO: ArrayListではなく、コレクションオブジェクトに変更
        List<String> list = kintaiTotalPrintRepository.execute();
        KintaiTotalPrint kintaiTotalPrint = new KintaiTotalPrint(kintaiTotalPrintInput, list);

        System.out.println(kintaiTotalPrint.getPrintStringForTotalWorkTime());
        System.out.println(kintaiTotalPrint.getPrintStringForTotalOverWorkTime());
    }
}
