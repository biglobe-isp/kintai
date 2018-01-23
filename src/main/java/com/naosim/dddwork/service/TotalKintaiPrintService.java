package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.InputKintai;
import com.naosim.dddwork.domain.TotalData;
import com.naosim.dddwork.domain.TotalKintaiFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TotalKintaiPrintService {

    @Autowired
    TotalKintaiFileRepository totalKintaiFileRepository;

    public void printTargetMonth(InputKintai inputKintai) throws IOException {
        // TODO: ArrayListではなく、コレクションオブジェクトに変更
        List<String> list = totalKintaiFileRepository.execute();
        TotalData totalData = new TotalData(inputKintai, list);

        System.out.println(totalData.getPrintStringForTotalWorkTime());
        System.out.println(totalData.getPrintStringForTotalOverWorkTime());
    }
}
