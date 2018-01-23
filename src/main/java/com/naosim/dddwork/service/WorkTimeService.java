package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeInputRepositoryFile;
import com.naosim.dddwork.datasource.WorkTimeTotalRepositoryFile;
import com.naosim.dddwork.domain.WorkTimeRepository;
import com.naosim.dddwork.domain.WorkTimeTotal;

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
                workTimeRepository = new WorkTimeInputRepositoryFile();
                workTimeRepository.doExecute(args);

            } else if("total".equals(methodType)) {
                //勤怠時間合計時間計算処理
                workTimeRepository = new WorkTimeTotalRepositoryFile();
                WorkTimeTotal workTimeTotal = (WorkTimeTotal) workTimeRepository.doExecute(args);

                System.out.println("勤務時間: " + workTimeTotal.getTotalWorkMinutes() / 60 + "時間" + workTimeTotal.getTotalWorkMinutes() % 60 + "分");
                System.out.println("残業時間: " + workTimeTotal.getTotalOverWorkMinutes()  / 60 + "時間" + workTimeTotal.getTotalOverWorkMinutes() % 60 + "分");

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
