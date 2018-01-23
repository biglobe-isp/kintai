package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.WorkTimeInputForm;
import com.naosim.dddwork.domain.WorkTimeTotal;
import com.naosim.dddwork.domain.WorkTimeTotalForm;
import com.naosim.dddwork.service.WorkTimeService;

import java.time.LocalDateTime;

public class WorkTimeApi {
    public void workTimeCalculate(String[] args) {

        //引数チェック
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        String methodType = args[0];
        WorkTimeService workTimeService = new WorkTimeService();

        if ("input".equals(methodType)) {

            if (args.length < 4) {
                throw new RuntimeException("引数が足りません");
            }

            WorkTimeInputForm workTimeInputParam = new WorkTimeInputForm(args[1], args[2], args[3], LocalDateTime.now().toString());
            workTimeService.workTimeInput(workTimeInputParam);

        } else if ("total".equals(methodType)) {

            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
            WorkTimeTotalForm workTimeTotalParam = new WorkTimeTotalForm(args[1]);
            WorkTimeTotal workTimeTotal = workTimeService.workTimeTotal(workTimeTotalParam);

            System.out.println("勤務時間: " + workTimeTotal.getTotalWorkMinutes() / 60 + "時間" + workTimeTotal.getTotalWorkMinutes() % 60 + "分");
            System.out.println("残業時間: " + workTimeTotal.getTotalOverWorkMinutes() / 60 + "時間" + workTimeTotal.getTotalOverWorkMinutes() % 60 + "分");


        } else {
            throw new RuntimeException("機能が存在しません。");
        }


    }
}
