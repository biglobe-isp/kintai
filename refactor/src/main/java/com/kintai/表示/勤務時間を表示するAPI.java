package com.kintai.表示;

public class 勤務時間を表示するAPI {

    public static void 表示する(String[] args){
        if(args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }

        String yearMonth = args[1];

        勤務時間を表示するService.表示する(yearMonth);

    }
}
