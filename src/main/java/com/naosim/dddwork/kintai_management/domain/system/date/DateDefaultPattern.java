package com.naosim.dddwork.kintai_management.domain.system.date;

enum DateDefaultPattern {
    yyyyMMdd("uuuuMMdd");

    private final String value;

    private DateDefaultPattern(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
