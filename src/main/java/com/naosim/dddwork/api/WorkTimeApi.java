package com.naosim.dddwork.api;

import com.naosim.dddwork.api.form.WorkTimeInputForm;
import com.naosim.dddwork.domain.worktotal.WorkTimeTotal;
import com.naosim.dddwork.api.form.WorkTimeTotalForm;
import com.naosim.dddwork.service.WorkTimeService;

import java.time.LocalDateTime;

public class WorkTimeApi {
    /**
     * 勤怠処理
     *
     * @param args
     */
    public void workTimeCalculate(String[] args) {

        //引数チェック
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        String methodType = args[0];
        WorkTimeService workTimeService = new WorkTimeService();

        if ("input".equals(methodType)) {
            //勤怠入力処理
            if (args.length < 4) {
                throw new RuntimeException("引数が足りません");
            }

            WorkTimeInputForm workTimeInputForm = new WorkTimeInputForm(args[1], args[2], args[3], LocalDateTime.now().toString());
            workTimeService.workTimeInput(workTimeInputForm.getValueObject());

        } else if ("total".equals(methodType)) {
            //勤怠合計時間計算処理
            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
            WorkTimeTotalForm workTimeTotalForm = new WorkTimeTotalForm(args[1]);
            WorkTimeTotal workTimeTotal = workTimeService.workTimeTotal(workTimeTotalForm.getValueObject());

            System.out.println("勤務時間: " + workTimeTotal.getTotalNormalWorkMinutes() / 60 + "時間" + workTimeTotal.getTotalNormalWorkMinutes() % 60 + "分");
            System.out.println("残業時間: " + workTimeTotal.getTotalOverWorkMinutes() / 60 + "時間" + workTimeTotal.getTotalOverWorkMinutes() % 60 + "分");

        } else {
            throw new RuntimeException("機能が存在しません。");
        }


    }
}
