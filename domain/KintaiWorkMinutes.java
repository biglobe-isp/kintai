package com.naosim.dddwork.domain;

//値オブジェクト
public class KintaiWorkMinutes {
    private int value;

    public KintaiWorkMinutes(int end) {
        this.value = end;
    }

    public int getValue() {
        return this.value;
    }

}
