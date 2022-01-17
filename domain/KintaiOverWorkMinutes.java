package com.naosim.dddwork.domain;

//値オブジェクト
public class KintaiOverWorkMinutes {
    private int value;

    public KintaiOverWorkMinutes(int overWorkMinutes) {
        this.value = overWorkMinutes;
    }

    public int getValue() {
        return this.value;
    }

}
