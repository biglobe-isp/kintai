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

        InputData inputData = new InputData(args);

        switch (inputData.getMethodType()) {
            case INPUT:
                RegistData registData = new RegistData(inputData);
                this.registKintaiFileRepository.execute(registData.getOutputData());

                break;

            case TOTAL:
                List<String> list = totalKintaiFileRepository.execute();
                TotalData totalData = new TotalData(inputData);
                totalData.printTotalData(list);

                break;

            default:
        }
    }
}
