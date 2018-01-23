package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.KintaiTotal;
import com.naosim.dddwork.domain.KintaiListRepository;
import com.naosim.dddwork.domain.KintaiTotalPrintInput;
import com.naosim.dddwork.domain.KintaiTotalPrintRepository;
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
        // TODO: ArrayListではなく、コレクションオブジェクトに変更
        List<String> list = this.kintaiListRepository.get();
        KintaiTotal kintaiTotal = new KintaiTotal(kintaiTotalPrintInput, list);

        this.kintaiTotalPrintRepository.print(kintaiTotal);
    }
}
