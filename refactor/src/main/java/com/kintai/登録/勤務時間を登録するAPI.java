package com.kintai.登録;

import java.time.LocalDateTime;

public class 勤務時間を登録するAPI {
    public static void 登録する(String[] args) {
        if(args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }
        //引数を受け取ってるAPI
        String date = args[1];
        String start = args[2];
        String end = args[3];
        String now = LocalDateTime.now().toString();

        勤務時間を登録するService.登録する(date, start, end, now);

    }
}
