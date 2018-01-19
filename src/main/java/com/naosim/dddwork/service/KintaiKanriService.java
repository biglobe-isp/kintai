package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.InputData;
import com.naosim.dddwork.domain.ProcessData;
import com.naosim.dddwork.domain.RegistData;
import com.naosim.dddwork.domain.TotalData;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class KintaiKanriService {

    public void execute(String[] args) throws IOException {

        InputData inputData = new InputData(args);

        ProcessData processData = null;
        switch (inputData.getMethodType()) {
            case INPUT:
                processData = new RegistData(inputData);
                break;

            case TOTAL:
                processData = new TotalData(inputData);
                break;

            default:
        }
        processData.execute();
    }
}
