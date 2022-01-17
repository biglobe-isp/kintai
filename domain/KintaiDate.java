package com.naosim.dddwork.domain;

//値オブジェクト
public class KintaiDate {
    private String value;

    public KintaiDate(String kintaiDate) {
        this.value = kintaiDate;
    }

    public String getValue() {
        return this.value;
    }
}
