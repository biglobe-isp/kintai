package jp.co.esumit.kintai.api;

import java.util.Arrays;

public enum MethodType {
    INPUT("input"),
    TOTAL("total");

    private String label;

     MethodType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static MethodType findBylabel(String label){
        for(MethodType methodType : values()){
            if( methodType.getLabel().equals(label)){
                return methodType;
            }
        }
        throw new RuntimeException("MethodTypeが不正です。");
    }
}
