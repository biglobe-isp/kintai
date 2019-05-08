package com.naosim.dddwork.kintai.api;

import java.util.Arrays;

public enum Request {

    INPUT,
    TOTAL,
    ;


    public String lowercaseName() {
        return name().toLowerCase();
    }

    public static String validRequestValues() {

        return Arrays.stream(values()).parallel().reduce(
                "",
                (validValues, value) -> validValues.isEmpty() ? value.lowercaseName() : validValues + ", " + value.lowercaseName(),
                (lhsValidValues, rhsValidValues) -> lhsValidValues + ", " + rhsValidValues
        );
    }
}
