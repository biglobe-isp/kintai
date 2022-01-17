package com.naosim.dddwork.domain;

import java.time.LocalDateTime;

//エンティティ
public class Kintai {
    private KintaiDate kintaiDate;
    private KintaiStart kintaiStart;
    private KintaiEnd kintaiEnd;
    public KintaiRegisterTime kintaiRegisterTime;
    public KintaiWorkMinutes workMinutes;
    public KintaiOverWorkMinutes overOverWorkMinutes;

    public Kintai(KintaiDate kintaiDate, KintaiStart kintaiStart, KintaiEnd kintaiEnd) {
        this.kintaiDate = kintaiDate;
        this.kintaiStart = kintaiStart;
        this.kintaiEnd = kintaiEnd;
        this.kintaiRegisterTime = new KintaiRegisterTime(LocalDateTime.now().toString());

        String start = kintaiStart.getValue();
        String end = kintaiEnd.getValue();

        int startH = Integer.valueOf(start.substring(0, 2));
        int startM = Integer.valueOf(start.substring(2, 4));

        int endH = Integer.valueOf(end.substring(0, 2));
        int endM = Integer.valueOf(end.substring(2, 4));

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
        int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);

        this.workMinutes = new KintaiWorkMinutes(workMinutes);
        this.overOverWorkMinutes = new KintaiOverWorkMinutes(overWorkMinutes);

    }

    public KintaiDate getKintaiDate() {
        return kintaiDate;
    }

    public KintaiStart getKintaiStart() {
        return kintaiStart;
    }

    public KintaiRegisterTime getkintaiRegisterTime() {
        return kintaiRegisterTime;
    }

    public KintaiEnd getKintaiEnd() {
        return kintaiEnd;
    }

    public KintaiWorkMinutes getKintaiWorkMinutes() {
        return workMinutes;
    }

    public KintaiOverWorkMinutes getKintaiOverWorkMinutes() {
        return overOverWorkMinutes;
    }



}
