package jp.co.biglobe.isp.kintai.api;

import java.util.Arrays;

public enum MethodType {
    INPUT("input"),
    TOTAL("total"),
    NONE("");
    private final String value;

    MethodType(String value) {
        this.value = value;
    }

    public static MethodType of(String str) {
        return Arrays.stream(MethodType.values())
                .filter(e->e.value.equals(str))
                .findFirst()
                .orElse(NONE);
    }
}
