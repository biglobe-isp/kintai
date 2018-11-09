package com.kintai.登録;

public class 勤務時間を登録するService {
    public static void 登録する(String date, String start, String end, String now) {

        int workMinutes = 勤務時間を登録するDomain.workMinutesを計算する(start, end);
        int overWorkMinutes = 勤務時間を登録するDomain.overWorkMinutesを計算する(workMinutes);

        勤務時間を登録するDatasource.登録する(date, start, end, workMinutes, overWorkMinutes, now);
    }
}
