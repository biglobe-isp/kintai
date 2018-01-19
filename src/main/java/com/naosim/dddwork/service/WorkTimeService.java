package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeRepositoryInput;
import com.naosim.dddwork.datasource.WorkTimeRepositoryTotal;
import com.naosim.dddwork.domain.WorkTimeRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WorkTimeService {
    public void workTimeCalculate(String[] args) {
        WorkTimeRepository workTimeRepository;

        try {
            if(args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if("input".equals(methodType)) {
                //勤怠入力処理
                workTimeRepository = new WorkTimeRepositoryInput();
                workTimeRepository.workTimeCalExec(args);

            } else if("total".equals(methodType)) {
                //勤怠時間合計時間計算処理
                workTimeRepository = new WorkTimeRepositoryTotal();
                workTimeRepository.workTimeCalExec(args);
            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
