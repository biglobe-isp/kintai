package com.naosim.dddwork.domain.entity;

import com.naosim.dddwork.domain.valueObject.KintaiDate;
import com.naosim.dddwork.domain.valueObject.KintaiEnd;
import com.naosim.dddwork.domain.valueObject.KintaiOverWorkMinutes;
import com.naosim.dddwork.domain.valueObject.KintaiRegisterTime;
import com.naosim.dddwork.domain.valueObject.KintaiStart;
import com.naosim.dddwork.domain.valueObject.KintaiWorkMinutes;

import java.time.LocalDateTime;

//エンティティ
public class Kintai {
    private KintaiDate kintaiDate;
    private KintaiStart kintaiStart;
    private KintaiEnd kintaiEnd;
    private KintaiRegisterTime kintaiRegisterTime;
    private KintaiWorkMinutes workMinutes;
    private KintaiOverWorkMinutes overOverWorkMinutes;

    public Kintai(KintaiDate kintaiDate, KintaiStart kintaiStart, KintaiEnd kintaiEnd) {
        this.kintaiDate = kintaiDate;
        this.kintaiStart = kintaiStart;
        this.kintaiEnd = kintaiEnd;
        this.kintaiRegisterTime = new KintaiRegisterTime(LocalDateTime.now().toString());
        this.workMinutes = new KintaiWorkMinutes(kintaiDate, kintaiStart, kintaiEnd);
        this.overOverWorkMinutes = new KintaiOverWorkMinutes(this.workMinutes);
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
