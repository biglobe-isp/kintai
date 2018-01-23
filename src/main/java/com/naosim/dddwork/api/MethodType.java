package com.naosim.dddwork.api;

public enum MethodType {
    INPUT,
    TOTAL;

    public static MethodType getMethodTypeFromString(String methodTypeStr) {
        try {
            return MethodType.valueOf(methodTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}

