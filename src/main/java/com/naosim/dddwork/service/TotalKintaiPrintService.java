package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.TotalData;
import com.naosim.dddwork.domain.TotalKintaiFileRepository;
import com.naosim.dddwork.domain.TotalKintaiPrintInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TotalKintaiPrintService {

    @Autowired
    TotalKintaiFileRepository totalKintaiFileRepository;

    public void printTargetMonth(TotalKintaiPrintInput totalKintaiPrintInput) throws IOException {
        // TODO: ArrayListではなく、コレクションオブジェクトに変更
        List<String> list = totalKintaiFileRepository.execute();
        TotalData totalData = new TotalData(totalKintaiPrintInput, list);

        System.out.println(totalData.getPrintStringForTotalWorkTime());
        System.out.println(totalData.getPrintStringForTotalOverWorkTime());
    }
}
