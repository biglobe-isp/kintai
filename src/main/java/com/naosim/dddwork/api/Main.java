package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.MonthlyTotalWorkTime;
import com.naosim.dddwork.service.KintaiService;

public class Main {
    public static void main(String[] args) {

        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }
        String methodType = args[0];

        if ("input".equals(methodType)) {
            if (args.length < 4) {
                throw new RuntimeException("引数が足りません");
            }
            String date = args[1];
            String start = args[2];
            String end = args[3];
            KintaiService.input(date, start, end);


        } else if ("total".equals(methodType)) {

            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
            String yearMonth = args[1];
            MonthlyTotalWorkTime kintai = KintaiService.total(yearMonth);

            System.out.println("勤務時間: " + kintai.getWorkTimeHour() + "時間" + kintai.getWorkTimeMinute() + "分");
            System.out.println("残業時間: " + kintai.getOverWorkTimeHour() + "時間" + kintai.getOverTimeMinute() + "分");


        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}