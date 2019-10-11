package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.MonthlyTotalWorkTime;
import com.naosim.dddwork.service.KintaiService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;

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
            input(args);
            /*
            LocalDate date = createLocalDate(args[1]);//YYYYMMDD
            LocalTime start = createLocalTime(args[2]);//HHMM
            LocalTime end = createLocalTime(args[3]);//HHMM
            KintaiService.input(date, start, end);
             */


        } else if ("total".equals(methodType)) {

            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
            String yearMonth = args[1];//YYYYMM
            MonthlyTotalWorkTime kintai = KintaiService.total(createYearMonth(yearMonth));

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
        int month = Integer.valueOf(yyyymmdd.substring(4, 6));
        int day = Integer.valueOf(yyyymmdd.substring(6, 8));
        return LocalDate.of(year, month, day);
    }

    private static YearMonth createYearMonth(String yyyymm) {
        int year = Integer.valueOf(yyyymm.substring(0, 4));
        int month = Integer.valueOf(yyyymm.substring(4, 6));
        return YearMonth.of(year, month);
    }

    private static void input(String[] args) {

        LocalDate date = null;//YYYYMMDD
        LocalTime start = null;//HHMM
        LocalTime end = null;//HHMM

        for (String agr : args) {
            String[] str = agr.split(":");
            switch (str[0]) {
                case "-date":
                    date = createLocalDate(extractDate(str));
                    break;
                case "-start":
                    start = createLocalTime(extractTime(str));
                    break;
                case "-end":
                    end = createLocalTime(extractTime(str));
                    break;
                default:

            }
        }
        if (date == null || start == null || end == null) {
            throw new IllegalArgumentException("引数の数が足りません。");
        }
        KintaiService.input(date, start, end);
    }

    private static String extractDate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }
        return args[1];
    }

    private static String extractTime(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();

        }
        return args[1].replaceAll("_", "");
    }

}