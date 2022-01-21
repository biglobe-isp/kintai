package com.naosim.dddwork.domain.valueObject;

//値オブジェクト
public class KintaiRegisterTime {
    private String value;

    public KintaiRegisterTime(String now) {
        this.value = now;
    }

    public String getValue() {
        return this.value;
    }

}
