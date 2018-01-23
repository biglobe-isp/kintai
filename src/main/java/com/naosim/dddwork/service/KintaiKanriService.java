package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class KintaiKanriService {

    @Autowired
    RegistKintaiFileRepository registKintaiFileRepository;

    @Autowired
    TotalKintaiFileRepository totalKintaiFileRepository;


    public void execute(String[] args) throws IOException {

        InputKintai inputKintai = new InputKintai(args);

        switch (inputKintai.getMethodType()) {
            case INPUT:
                RegistData registData = new RegistData(inputKintai);
                this.registKintaiFileRepository.execute(registData.getOutputData());

                break;

            case TOTAL:
                List<String> list = totalKintaiFileRepository.execute();
                TotalData totalData = new TotalData(inputKintai, list);

                System.out.println(totalData.getPrintStringForTotalWorkTime());
                System.out.println(totalData.getPrintStringForTotalOverWorkTime());

                break;

            default:
        }
    }
}
