package com.naosim.dddwork.api;

import com.naosim.dddwork.service.KintaiService;
import java.util.Arrays;

public class KintaiApi {

    KintaiService service;

    public KintaiApi() {
        service = new KintaiService();
    }

    public void Total(String yearMonth) {

        // バリデーション
        if (!yearMonth.matches("[0-9]{6}")) {
            throw new RuntimeException("集計対象の年月をYYYYMMの形式で指定してください");
        }
        // サービス呼出し
        this.service.total(yearMonth);
    }

    public void Input(String[] params) {
        System.out.println(String.format("api/Input params: %s", Arrays.toString(params)));
        String date  = "";
        String start = "";
        String end = "";
        for (String param : params) {
             if (param.matches("-date:[0-9]{8}")) {
                 date = param.substring(6,14);
             }
             if (param.matches("-start:[0-9]{2}_[0-9]{2}")) {
                 start = param.substring(7,9) + param.substring(10,12);
             }
             if (param.matches("-end:[0-9]{2}_[0-9]{2}")) {
                 end = param.substring(5,7) + param.substring(8,10);
             }
        }
        if (date.isEmpty()) {
            throw new RuntimeException("対象の日付を-date:YYYYMMDDの形式で指定してください");
        }
        if (start.isEmpty()) {
            throw new RuntimeException("勤務開始時間を-start:HH_SSの形式で指定してください");
        }
        if (end.isEmpty()) {
            throw new RuntimeException("勤務終了時間を-end:HH_SSの形式で指定してください");
        }

        // サービス呼出し
        this.service.regist(date, start, end);
    }
}