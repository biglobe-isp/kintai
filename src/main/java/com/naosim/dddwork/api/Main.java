package com.naosim.dddwork.api;

import com.naosim.dddwork.service.KintaiService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
            int[] kintai = KintaiService.total(yearMonth);

            System.out.println("勤務時間: " + kintai[0] / 60 + "時間" + kintai[0] % 60 + "分");
            System.out.println("残業時間: " + kintai[1] / 60 + "時間" + kintai[1] % 60 + "分");


        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}