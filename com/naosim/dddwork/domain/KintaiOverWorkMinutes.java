package com.naosim.dddwork.domain;

//値オブジェクト
public class KintaiOverWorkMinutes {
    private int value;
    private static final int WORK_HOURS = 8;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int DEFAULT_WORK_MINUTES = WORK_HOURS * MINUTES_PER_HOUR;

    public KintaiOverWorkMinutes(KintaiWorkMinutes kintaiWorkMinutes) {
        this.value = Math.max(kintaiWorkMinutes.getValue() - DEFAULT_WORK_MINUTES, 0);
    }

    public KintaiOverWorkMinutes(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
