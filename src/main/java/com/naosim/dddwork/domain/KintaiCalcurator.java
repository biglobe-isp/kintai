package com.naosim.dddwork.domain;

import com.naosim.dddwork.datasouce.IKintai;

public class KintaiCalcurator implements IKintai {
    //勤務日
    private WorkDate date;
    //開始時刻
    private WorkTime start;
    //終了時刻
    private WorkTime end;

    //TODO 休憩時間のロジックは何とかしたい（計算方法とかとか）
    //TODO 勤怠のファクトリクラスを作成
    public KintaiCalcurator(String date, String start, String end) {
        this.date = new WorkDate(date);
        this.start = new WorkTime(start);
        this.end = new WorkTime(end);

    }

    private int calcurateJiturodo() {
        int endH = this.end.getHour();
        int endM = this.end.getMinute();
        int startH = this.start.getHour();
        int startM = this.start.getMinute();

        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= 60;
        }

        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= 60;
        }

        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= 60;
        }

        return workMinutes;

    }

    private int calcurateZangyo() {
        int workMinutes = calcurateJiturodo();
        return Math.max(workMinutes - 8 * 60, 0);

    }

    @Override
    public String getWorkMinutes() {
        return String.valueOf(calcurateJiturodo());
    }

    @Override
    public String getOverWorkMinutes() {
        return String.valueOf(calcurateZangyo());
    }

    @Override
    public String getDate() {
        return this.date.toString();
    }

    @Override
    public String getStart() {
        return this.start.toString();
    }

    @Override
    public String getEnd() {
        return this.end.toString();
    }
}
