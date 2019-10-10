package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.MonthlyTotalWorkTime;
import com.naosim.dddwork.service.KintaiService;

import java.time.LocalDate;
import java.time.LocalTime;

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
            LocalDate date = createLocalDate(args[1]);//YYYYMMDD
            LocalTime start = createLocalTime(args[2]);//HHMM
            LocalTime end = createLocalTime(args[3]);//HHMM
            KintaiService.input(date, start, end);


        } else if ("total".equals(methodType)) {

            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
            String yearMonth = args[1];//YYYYMM
            MonthlyTotalWorkTime kintai = KintaiService.total(createLocalDate_(yearMonth));

            System.out.println("勤務時間: " + kintai.getWorkTimeHour() + "時間" + kintai.getWorkTimeMinute() + "分");
            System.out.println("残業時間: " + kintai.getOverWorkTimeHour() + "時間" + kintai.getOverWorkTimeMinute() + "分");


        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }

    private static LocalTime createLocalTime(String hhmm) {
        int hour = Integer.valueOf(hhmm.substring(0, 2));
        int minute = Integer.valueOf(hhmm.substring(2, 4));
        return LocalTime.of(hour, minute);
    }

    private static LocalDate createLocalDate(String yyyymmdd) {
        int year = Integer.valueOf(yyyymmdd.substring(0, 4));
        int mouth = Integer.valueOf(yyyymmdd.substring(4, 6));
        int day = Integer.valueOf(yyyymmdd.substring(6, 8));
        return LocalDate.of(year, mouth, day);
    }

    private static LocalDate createLocalDate_(String yyyymm) {
        int year = Integer.valueOf(yyyymm.substring(0, 4));
        int mouth = Integer.valueOf(yyyymm.substring(4, 6));
        return LocalDate.of(year, mouth, 1);
    }

}